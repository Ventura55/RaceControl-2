package org.control.racecontrol.domain.port.input;

import org.control.racecontrol.domain.model.Race;

public interface CreateRaceUseCase {
    void createRace(Race race);
}
