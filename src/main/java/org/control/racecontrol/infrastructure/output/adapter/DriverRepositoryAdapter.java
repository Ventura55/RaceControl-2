package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.domain.port.output.DriverRepository;
import org.control.racecontrol.infrastructure.output.entity.DriverEntity;
import org.control.racecontrol.infrastructure.output.mapper.DriverPersistenceMapper;
import org.control.racecontrol.infrastructure.output.repository.DataDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverRepositoryAdapter implements DriverRepository {
    @Autowired
    private DataDriverRepository repository;

    @Autowired
    private DriverPersistenceMapper mapper;

    @Override
    public long countDriversByIdTeam(long idTeam) {
        return repository.countByIdTeam(idTeam);
    }

    @Override
    public void save(Driver driver) {
        DriverEntity entity = mapper.toEntity(driver);
        repository.save(entity);
    }
}
