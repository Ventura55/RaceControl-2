package org.control.racecontrol.infrastructure.output.mapper;

import org.control.racecontrol.domain.model.Team;
import org.control.racecontrol.infrastructure.output.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamPersistenceMapper {
    public TeamEntity toEntity(Team team) {
        if (team == null) return null;

        TeamEntity entity = new TeamEntity();
        entity.setName(team.getName());
        entity.setEngineSupplier(team.getEngineSupplier());
        return entity;
    }

    public Team toDomain(TeamEntity entity) {
        if (entity == null) return null;

        Team domain = new Team();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setEngineSupplier(entity.getEngineSupplier());
        return domain;
    }
}
