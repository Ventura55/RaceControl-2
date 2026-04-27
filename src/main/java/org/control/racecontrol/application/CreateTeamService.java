package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Team;
import org.control.racecontrol.domain.port.input.CreateTeamUseCase;
import org.control.racecontrol.domain.port.output.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateTeamService implements CreateTeamUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreateTeamService.class);

    private TeamRepository teamRepository;

    public CreateTeamService (TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void createExecute(Team team) {
        try {
            log.info("Se ha podido crear correctamente con el id {}", team.getId());
            teamRepository.save(team);
        } catch (Exception e) {
            log.warn("No se ha podido crear");
            throw e;
        }
    }
}
