package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.input.CreateRaceResultUseCase;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateRaceResultService implements CreateRaceResultUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreateRaceResultService.class);

    private RaceResultRepository resultRepository;

    public CreateRaceResultService (RaceResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public void createRaceResult(RaceResult result) {
        log.info("Iniciando la creacion del resultado de la carrera");

        // La posicion de salida tiene que ser entre 1 y 20
        if (result.getGridPosition() < 1 || result.getGridPosition() > 20) {
            log.warn("No se ha podido completar, la posicion de salida tiene que ser entre 1 y 20");
            throw new IllegalArgumentException("La posicion de salida tiene que ser entre 1 y 20");
        }

        if (result.getStatus() == RaceResult.Status.FINISHED) {
            // posicion final tiene que estar entre 1 y 20\
            if (result.getFinalPosition() < 1 || result.getFinalPosition() > 20) {
                log.warn("No se ha podido completar, la posicion final tiene que ser entre 1 y 20");
                throw new IllegalArgumentException("La posicion final tiene que ser entre 1 y 20");
            }

            boolean positionOccupied = resultRepository.existsByRaceIdAndFinalPositionAndStatus(result.getIdRace(), result.getFinalPosition(), RaceResult.Status.FINISHED);
            log.debug("Comprobando si hay posiciones finales repetidas");

            if (positionOccupied) {
                log.warn("La posicion esta ocupada y no se ha podido completar");
                throw new IllegalArgumentException("La posicion " + result.getFinalPosition() + " ya esta ocupada");
            }
        }

        resultRepository.save(result);
    }
}
