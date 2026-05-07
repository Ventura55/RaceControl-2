package org.control.racecontrol.infrastructure.input.rest.mapper;

import org.control.racecontrol.domain.model.Team;
import org.control.racecontrol.infrastructure.input.rest.dto.request.TeamRequestDto;
import org.springframework.stereotype.Component;

@Component
public class TeamRestMapper {
    public Team toDomain(TeamRequestDto dto) {
        if (dto == null) return null;

        Team teamDomain = new Team(dto.getName(), dto.getEngineSupplier());
        return teamDomain;
    }
}
