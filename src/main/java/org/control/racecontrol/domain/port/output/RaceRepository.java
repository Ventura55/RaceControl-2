package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.Race;

import java.util.Optional;

public interface RaceRepository {
    void save(Race race);

    Optional<Race> findById(Long id);
}
