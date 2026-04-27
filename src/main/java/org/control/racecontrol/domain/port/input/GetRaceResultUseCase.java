package org.control.racecontrol.domain.port.input;

import org.control.racecontrol.domain.model.RaceResult;

import java.util.List;

public interface GetRaceResultUseCase {
    List<RaceResult> getAll();
}
