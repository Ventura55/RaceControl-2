package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.FantasyRoster;

import java.util.Optional;

public interface FantasyRosterRepository {
    Optional<FantasyRoster> findByUsername(String username);
    void save(FantasyRoster roster);
}
