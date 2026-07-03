package org.control.racecontrol.domain.port.input;

import org.control.racecontrol.domain.model.RaceInsight;

public interface GenerateRaceInsightsUseCase {
    String execute(Long raceId);
}
