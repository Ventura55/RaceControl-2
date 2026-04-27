package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Team;
import org.control.racecontrol.domain.port.input.CreateTeamUseCase;
import org.control.racecontrol.domain.port.output.TeamRepository;

public class CreateTeamService implements CreateTeamUseCase {
    private TeamRepository teamRepository;

    public CreateTeamService (TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void createExecute(Team team) {
        teamRepository.save(team);
    }
}
