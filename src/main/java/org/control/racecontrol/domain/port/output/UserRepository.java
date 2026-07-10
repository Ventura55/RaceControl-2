package org.control.racecontrol.domain.port.output;

import java.util.Optional;

public interface UserRepository {
    Optional<Long> findTeamIdByUsername(String username);
}