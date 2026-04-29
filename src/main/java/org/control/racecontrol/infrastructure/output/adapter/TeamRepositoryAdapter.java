package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.Team;
import org.control.racecontrol.domain.port.output.TeamRepository;
import org.control.racecontrol.infrastructure.output.entity.TeamEntity;
import org.control.racecontrol.infrastructure.output.mapper.TeamPersistenceMapper;
import org.control.racecontrol.infrastructure.output.repository.DataTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamRepositoryAdapter implements TeamRepository {
    @Autowired
    private DataTeamRepository repository;

    @Autowired
    private TeamPersistenceMapper mapper;

    @Override
    public void save(Team team) {
        TeamEntity entity = mapper.toEntity(team);
        repository.save(entity);
    }
}
