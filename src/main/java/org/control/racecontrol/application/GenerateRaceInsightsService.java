package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Race;
import org.control.racecontrol.domain.model.RaceDataSnapshot;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.output.PenaltyRepository;
import org.control.racecontrol.domain.port.output.RaceReportGenerator;
import org.control.racecontrol.domain.port.output.RaceRepository;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GenerateRaceInsightsService {
    private final RaceReportGenerator reportGenerator;
    private final RaceRepository raceRepository;
    private final RaceResultRepository resultRepository;
    private final PenaltyRepository penaltyRepository;

    public GenerateRaceInsightsService(RaceReportGenerator reportGenerator, RaceRepository raceRepository, RaceResultRepository resultRepository, PenaltyRepository penaltyRepository) {
        this.reportGenerator = reportGenerator;
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
        this.penaltyRepository = penaltyRepository;
    }

    public String execute(Long raceId) {
        Race race = raceRepository.findById(raceId).orElseThrow(() -> new IllegalArgumentException("La carrera no funciona con la ID: " + raceId));

        List<String> podium = resultRepository.findAll(raceId).stream().sorted()
                .sorted(Comparator.comparingInt(RaceResult::getFinalPosition))
                .limit(3).map(result -> String.valueOf(result.getIdDriver())).toList();

        List<String> penalties = penaltyRepository.findByRaceId(raceId).stream()
                .map(penalty -> "Sancion al coche " + penalty.getId() + " por " + penalty.getReason()).toList();

        RaceDataSnapshot snapshot = new RaceDataSnapshot(race.getName(), podium, penalties);

        return reportGenerator.generateRaceSummary(snapshot);
    }
}
