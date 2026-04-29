package org.control.racecontrol.infrastructure.output.mapper;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.infrastructure.output.entity.RaceResultEntity;
import org.springframework.stereotype.Component;

@Component
public class RaceResultPersistenceMapper {
    public RaceResultEntity toEntity(RaceResult result) {
        if (result == null) return null;

        RaceResultEntity entity = new RaceResultEntity();
        entity.setId(result.getId());
        entity.setIdRace(result.getIdRace());
        entity.setIdDriver(result.getIdDriver());
        entity.setGridPosition(result.getGridPosition());
        entity.setFinalPosition(result.getFinalPosition());
        entity.setFastestLap(result.isFastestLap());
        entity.setStatus(result.getStatus());
        return entity;
    }
}
