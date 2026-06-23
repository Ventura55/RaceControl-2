package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.domain.port.output.PenaltyRepository;
import org.control.racecontrol.infrastructure.output.entity.PenaltyEntity;
import org.control.racecontrol.infrastructure.output.mapper.PenaltyPersistenceMapper;
import org.control.racecontrol.infrastructure.output.repository.DataPenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PenaltyRepositoryAdapter implements PenaltyRepository {
    @Autowired
    private DataPenaltyRepository repository;

    @Autowired
    private PenaltyPersistenceMapper mapper;

    @Override
    public void save(Penalty penalty) {
        PenaltyEntity entity = mapper.toEntity(penalty);
        repository.save(entity);
    }

    @Override
    public long countByRaceResultId(long raceResultId) {
        return 0;
    }

    @Override
    public List<Penalty> findByRaceId(Long raceId) {
        List<PenaltyEntity> entities = repository.findByRaceId(raceId);
        return entities.stream().map(entity -> mapper.toDomain(entity)).toList();
    }
}
