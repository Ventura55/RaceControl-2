package org.control.racecontrol.infrastructure.output.mapper;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.infrastructure.output.entity.PenaltyEntity;
import org.springframework.stereotype.Component;

@Component
public class PenaltyPersistenceMapper {
    public PenaltyEntity toEntity(Penalty penalty) {
        if (penalty == null) return null;

        PenaltyEntity entity = new PenaltyEntity();
        entity.setId(penalty.getId());
        entity.setIdRaceResult(penalty.getIdRaceResult());
        entity.setSeconds(penalty.getPentaltySeconds());
        entity.setReason(penalty.getReason());
        return entity;
    }

    public Penalty toDomain(PenaltyEntity entity) {
        if (entity == null) return null;

        Penalty penalty = new Penalty();
        penalty.setId(entity.getId());
        penalty.setIdRaceResult(entity.getIdRaceResult());
        penalty.setPentaltySeconds(entity.getSeconds());
        penalty.setReason(entity.getReason());

        return penalty;
    }
}
