package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Race;
import org.control.racecontrol.domain.port.input.CreateRaceUseCase;
import org.control.racecontrol.domain.port.output.RaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateRaceService implements CreateRaceUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreateRaceService.class);

    private RaceRepository raceRepository;

    public CreateRaceService (RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public void createRace(Race race) {
        try {
            log.info("Se ha podido crear correctamente la carrera con el id {}", race.getId());
            raceRepository.save(race);
        } catch (Exception e) {
            log.warn("No se ha podido cargar en la bd");
            throw e;
        }
    }
}
