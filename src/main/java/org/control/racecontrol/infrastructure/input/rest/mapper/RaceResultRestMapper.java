package org.control.racecontrol.infrastructure.input.rest.mapper;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.infrastructure.input.rest.dto.request.RaceResultRequestDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RaceResultRestMapper {

    public RaceResult toDomain(Long raceId, RaceResultRequestDto dto) {
        if (dto == null) return null;

        RaceResult result = new RaceResult(null, raceId, dto.getDriverNumber(), dto.getGridPosition(), dto.getFinalPosition(), dto.isFastestLap(), 0, dto.getStatus());
        return result;
    }
}
