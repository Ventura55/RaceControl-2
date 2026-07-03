package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.AiAuditLog;
import org.control.racecontrol.domain.model.Race;
import org.control.racecontrol.domain.model.RaceDataSnapshot;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.input.GenerateRaceInsightsUseCase;
import org.control.racecontrol.domain.port.output.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class GenerateRaceInsightsService implements GenerateRaceInsightsUseCase {
    private final RaceReportGenerator reportGenerator;
    private final RaceRepository raceRepository;
    private final RaceResultRepository resultRepository;
    private final PenaltyRepository penaltyRepository;
    private final AiAuditRepository auditRepository;

    public GenerateRaceInsightsService(RaceReportGenerator reportGenerator, RaceRepository raceRepository, RaceResultRepository resultRepository, PenaltyRepository penaltyRepository, AiAuditRepository auditRepository) {
        this.reportGenerator = reportGenerator;
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
        this.penaltyRepository = penaltyRepository;
        this.auditRepository = auditRepository;
    }

    @Override
    public String execute(Long raceId) {
        long startTime = System.currentTimeMillis();
        String username = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : "usuario_sistema";

        // Preparamos los datos
        Race race = raceRepository.findById(raceId).orElseThrow();
        List<String> podium = resultRepository.findAll(raceId).stream()
                .sorted(Comparator.comparingInt(RaceResult::getFinalPosition))
                .limit(3).map(result -> String.valueOf(result.getIdDriver())).toList();
        List<String> penalties = penaltyRepository.findByRaceId(raceId).stream()
                .map(penalty -> "Sancion al coche " + penalty.getId() + " por " + penalty.getReason()).toList();

        RaceDataSnapshot snapshot = new RaceDataSnapshot(race.getName(), podium, penalties);

        try {
            // Intentamos generar el reporte
            String report = reportGenerator.generateRaceSummary(snapshot);

            long durationMs = System.currentTimeMillis() - startTime;
            auditRepository.save(new AiAuditLog(raceId, username, LocalDateTime.now(), durationMs, "SUCCESS"));

            return report;

        } catch (Exception e) {
            System.err.println("🚨 ERROR REAL DETECTADO: " + e.getMessage());
            e.printStackTrace(); // Esto nos dará la pista definitiva

            long durationMs = System.currentTimeMillis() - startTime;
            auditRepository.save(new AiAuditLog(raceId, username, LocalDateTime.now(), durationMs, "ERROR_DESCONOCIDO"));

            throw new RuntimeException("Falló la IA. Motivo real: " + e.getMessage(), e);
        }
    }
}
