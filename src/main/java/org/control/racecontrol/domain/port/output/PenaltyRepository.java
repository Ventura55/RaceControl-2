package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.Penalty;

import java.util.List;

public interface PenaltyRepository {
    void save(Penalty penalty);

    long countByRaceResultId(long raceResultId);

    List<Penalty> findByRaceId(Long raceId);
}
