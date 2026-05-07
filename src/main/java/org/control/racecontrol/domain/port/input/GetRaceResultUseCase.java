package org.control.racecontrol.domain.port.input;

import org.control.racecontrol.domain.model.RaceResultResponse;

import java.util.List;

public interface GetRaceResultUseCase {
    List<RaceResultResponse> getAll();
}
