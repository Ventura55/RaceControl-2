package org.control.racecontrol.domain.port.input;

import org.control.racecontrol.domain.model.RaceResult;

public interface CreateRaceResultUseCase {
    void createRaceResult(RaceResult result);
}
