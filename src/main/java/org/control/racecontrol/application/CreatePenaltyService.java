package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.input.CreatePenaltyUseCase;
import org.control.racecontrol.domain.port.output.PenaltyRepository;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CreatePenaltyService implements CreatePenaltyUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreatePenaltyService.class);

    private PenaltyRepository penaltyRepository;
    private final RaceResultRepository raceResultRepository;

    public CreatePenaltyService (PenaltyRepository penaltyRepository, RaceResultRepository raceResultRepository) {
        this.penaltyRepository = penaltyRepository;
        this.raceResultRepository = raceResultRepository;
    }

    public void createPenalty(Long raceId, Integer driverNumber, Penalty penalty) {
        log.info("Iniciando la creacion de la falta para el piloto {} en la carrera {}", driverNumber, raceId);

        Optional<RaceResult> result = raceResultRepository.findByRaceIdAndDriverNumber(raceId, driverNumber);

        if (result.isEmpty()) {
            log.warn("No se puede aplicar penalización: El piloto no tiene resultados en esta carrera");
            throw new IllegalArgumentException("No existe un registro de carrera para este piloto");
        }

        try {
            penalty.setIdRaceResult(result.get().getId());

            penaltyRepository.save(penalty);
            log.info("La falta se ha creado correctamente vinculada al resultado ID: {}", penalty.getIdRaceResult());
        } catch (Exception e) {
            log.error("Error: No se ha podido guardar la penalización en la BD", e);
            throw e;
        }
    }


    @Override
    public void createPenalty(Penalty penalty) {
        log.info("Iniciando la creacion de la falta");

        try {
            penaltyRepository.save(penalty);
            log.info("La falta se ha creado correctamente");
        } catch (Exception e) {
            log.error("Error no se ha podido crear correctamente en la bd");
            throw e;
        }
    }
}
