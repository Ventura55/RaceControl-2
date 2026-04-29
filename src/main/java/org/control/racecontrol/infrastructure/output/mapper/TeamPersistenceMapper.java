package org.control.racecontrol.infrastructure.output.mapper;

import org.control.racecontrol.domain.model.Team;
import org.control.racecontrol.infrastructure.output.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamPersistenceMapper {
    public TeamEntity toEntity(Team team) {
        if (team == null) return null;

        TeamEntity entity = new TeamEntity();
        entity.setId(team.getId());
        entity.setName(team.getName());
        entity.setEngineSupplier(team.getEngineSupplier());
        return entity;
    }
}
