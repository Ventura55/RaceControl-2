package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.input.CreateRaceResultUseCase;
import org.control.racecontrol.domain.port.output.RaceResultRepository;

public class CreateRaceResultService implements CreateRaceResultUseCase {
    private RaceResultRepository resultRepository;

    public CreateRaceResultService (RaceResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public void createRaceResult(RaceResult result) {
        // La posicion de salida tiene que ser entre 1 y 20
        if (result.getGridPosition() < 1 || result.getGridPosition() > 20) {
            throw new IllegalArgumentException("La posicion de salida tiene que ser entre 1 y 20");
        }

        if (result.getStatus() == RaceResult.Status.FINISHED) {
            // posicion final tiene que estar entre 1 y 20\
            if (result.getFinalPosition() > 1 || result.getFinalPosition() < 20) {
                throw new IllegalArgumentException("La posicion final tiene que ser entre 1 y 20");
            }

            boolean positionOccupied = resultRepository.existsByRaceIdAndFinalPositionAndStatus(result.getIdRace(), result.getFinalPosition(), RaceResult.Status.FINISHED);

            if (positionOccupied) throw new IllegalArgumentException("La posicion " + result.getFinalPosition() + " ya esta ocupada");
        }
    }
}
