package org.control.racecontrol.infrastructure.input.rest.mapper;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.infrastructure.input.rest.dto.request.PenaltyRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PenaltyRestMapper {
    public Penalty toDomain(Long raceId, Integer driverNumber, PenaltyRequestDto dto) {
        if (dto == null) return null;

        return new Penalty(0L, 0L, dto.getPenaltySeconds(), dto.getReason());
    }
}
