package org.control.racecontrol.infrastructure.output.mapper;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.infrastructure.output.entity.DriverEntity;
import org.springframework.stereotype.Component;

@Component
public class DriverPersistenceMapper {
    public DriverEntity toEntity(Driver domain) {
        if (domain == null) return null;

        DriverEntity entity = new DriverEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setIdTeam(domain.getIdTeam());
        return entity;
    }
}
