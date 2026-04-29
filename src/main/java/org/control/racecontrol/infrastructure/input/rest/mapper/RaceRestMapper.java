package org.control.racecontrol.infrastructure.input.rest.mapper;

import org.control.racecontrol.domain.model.Race;
import org.control.racecontrol.infrastructure.input.rest.dto.request.RaceRequestDto;
import org.springframework.stereotype.Component;

@Component
public class RaceRestMapper {
    public Race toDomain(RaceRequestDto dto) {
        if (dto == null) return null;

        Race race = new Race(dto.getId(), dto.getName(), dto.getTotalLaps());
        return race;
    }
}
