package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.RaceDataSnapshot;

public interface RaceReportGenerator {
    String generateRaceSummary(RaceDataSnapshot data);
}
