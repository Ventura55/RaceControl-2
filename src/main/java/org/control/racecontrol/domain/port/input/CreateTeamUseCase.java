package org.control.racecontrol.domain.port.input;

import org.control.racecontrol.domain.model.Team;

public interface CreateTeamUseCase {
    void createExecute(Team team);
}
