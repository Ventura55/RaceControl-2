package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.RaceInsight;

public interface AiServiceRepository {
    RaceInsight faceInsight(String raceId);
}
