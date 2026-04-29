package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.control.racecontrol.infrastructure.output.mapper.TeamPersistenceMapper;
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
    private TeamPersistenceMapper mapper;

    @Override
    public void save(RaceResult result) {

    }

    @Override
    public int findFinalPositionByIdDriver(int idDriver) {
        return 0;
    }

    @Override
    public Optional<RaceResult> findByIdDriver(int idDriver) {
        return Optional.empty();
    }

    @Override
    public List<RaceResult> findAll() {
        return List.of();
    }

    @Override
    public boolean existsByRaceIdAndFinalPositionAndStatus(long idRace, int finalPosition, RaceResult.Status status) {
        return false;
    }
}
