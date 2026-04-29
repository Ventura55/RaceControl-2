package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.Race;
import org.control.racecontrol.domain.port.output.RaceRepository;
import org.control.racecontrol.infrastructure.output.entity.RaceEntity;
import org.control.racecontrol.infrastructure.output.mapper.RacePersistenceMapper;
import org.control.racecontrol.infrastructure.output.repository.DataRaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceRepositoryAdapter implements RaceRepository {
    @Autowired
    private DataRaceRepository repository;

    @Autowired
    private RacePersistenceMapper mapper;

    @Override
    public void save(Race race) {
        RaceEntity entity = mapper.toEntity(race);
        repository.save(entity);
    }
}
