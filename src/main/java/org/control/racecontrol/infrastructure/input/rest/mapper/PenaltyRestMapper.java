package org.control.racecontrol.infrastructure.input.rest.mapper;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.infrastructure.input.rest.dto.request.PenaltyRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PenaltyRestMapper {
    public Penalty toDomain(PenaltyRequestDto dto) {
        if (dto == null) return null;

        Penalty penalty = new Penalty(dto.getId(), dto.getIdRaceResult(), dto.getPentaltySeconds(), dto.getReason());
        return penalty;
    }
}
