package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.control.racecontrol.infrastructure.output.entity.RaceResultEntity;
import org.control.racecontrol.infrastructure.output.mapper.RaceResultPersistenceMapper;
import org.control.racecontrol.infrastructure.output.repository.DataRaceResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RaceResultRepositoryAdapter implements RaceResultRepository {
    @Autowired
    private DataRaceResultRepository repository;

    @Autowired
    private RaceResultPersistenceMapper mapper;

    @Override
    public void save(RaceResult result) {
        RaceResultEntity entity = mapper.toEntity(result);
        repository.save(entity);
    }

    @Override
    public int findFinalPositionByIdDriver(int idDriver) {
        return 0;
    }

    @Override
    public Optional<RaceResult> findByIdDriver(int idDriver) {
        return repository.findByIdDriver(idDriver);
    }

    @Override
    public List<RaceResult> findAll(long idRace) {
        return repository.findAllByIdRace(idRace);
    }

    @Override
    public boolean existsByRaceIdAndFinalPositionAndStatus(long idRace, int finalPosition, RaceResult.Status status) {
        return repository.existsByIdRaceAndFinalPositionAndStatus(idRace, finalPosition, status);
    }
}
