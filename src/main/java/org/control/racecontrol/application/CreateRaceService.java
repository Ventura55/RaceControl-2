package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Race;
import org.control.racecontrol.domain.port.input.CreateRaceUseCase;
import org.control.racecontrol.domain.port.output.RaceRepository;

public class CreateRaceService implements CreateRaceUseCase {
    private RaceRepository raceRepository;

    public CreateRaceService (RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public void createRace(Race race) {
        raceRepository.save(race);
    }
}
