package org.control.racecontrol.infrastructure.output.mapper;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.infrastructure.output.entity.DriverEntity;
import org.springframework.stereotype.Component;

@Component
public class DriverPersistenceMapper {
    public DriverEntity toEntity(Driver domain) {
        if (domain == null) return null;

        DriverEntity entity = new DriverEntity();
        entity.setName(domain.getName());
        entity.setIdTeam(domain.getIdTeam());
        entity.setMarketValue(domain.getMarketValue());
        return entity;
    }

    public Driver toDomain(DriverEntity entity) {
        if (entity == null) return null;

        Driver domain = new Driver();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setIdTeam(entity.getIdTeam());
        domain.setMarketValue(entity.getMarketValue());
        return domain;
    }
}
