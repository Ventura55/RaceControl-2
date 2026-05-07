package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.model.RaceResultResponse;
import org.control.racecontrol.domain.model.Team;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.control.racecontrol.infrastructure.output.entity.DriverEntity;
import org.control.racecontrol.infrastructure.output.entity.RaceResultEntity;
import org.control.racecontrol.infrastructure.output.entity.TeamEntity;
import org.control.racecontrol.infrastructure.output.mapper.DriverPersistenceMapper;
import org.control.racecontrol.infrastructure.output.mapper.RaceResultPersistenceMapper;
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
    private RaceResultPersistenceMapper mapper;

    @Autowired
    private TeamPersistenceMapper teamMapper;

    @Autowired
    private DriverPersistenceMapper driverMapper;

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
    public Optional<RaceResult> findByRaceIdAndDriverNumber(long idRace, int idDriver) {
        return repository.findByIdRaceAndIdDriver(idRace, idDriver).map(mapper::toDomain);
    }

    @Override
    public Optional<RaceResult> findByIdDriver(int idDriver) {
        return repository.findByIdDriver(idDriver).map(mapper::toDomain);
    }

    @Override
    public List<RaceResult> findAll(long idRace) {
        return repository.findAllByIdRace(idRace).stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByRaceIdAndFinalPositionAndStatus(long idRace, int finalPosition, RaceResult.Status status) {
        return repository.existsByIdRaceAndFinalPositionAndStatus(idRace, finalPosition, status);
    }

    @Override
    public List<RaceResultResponse> findAllWithDriverAndTeam() {
        return repository.findAllWithDriverAndTeam().stream()
                .map(row -> {
                    RaceResultEntity raceResultEntity = (RaceResultEntity) row[0];
                    DriverEntity driverEntity = (DriverEntity) row[1];
                    TeamEntity teamEntity = (TeamEntity) row[2];

                    RaceResult result = mapper.toDomain(raceResultEntity);

                    Driver driver = driverMapper.toDomain(driverEntity);

                    Team team = teamMapper.toDomain(teamEntity);

                    return new RaceResultResponse(result, driver, team);
                })
                .toList();
    }
}
