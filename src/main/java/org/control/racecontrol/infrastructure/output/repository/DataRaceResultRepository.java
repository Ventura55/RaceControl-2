package org.control.racecontrol.infrastructure.output.repository;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.infrastructure.output.entity.RaceResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRaceResultRepository extends JpaRepository<RaceResultEntity, Long> {
    Optional<RaceResultEntity> findByIdDriver(long idDriver);

    List<RaceResultEntity> findAllByIdRace(long idRace);

    Optional<RaceResultEntity> findByIdRaceAndIdDriver(Long raceId, Integer driverId);

    boolean existsByIdRaceAndFinalPositionAndStatus(Long idRace, Integer finalPosition, RaceResult.Status status);

    @Query("SELECT r, d, t " +
            "FROM RaceResultEntity r " +
            "JOIN DriverEntity d ON r.idDriver = d.id " +
            "JOIN TeamEntity t ON d.idTeam = t.id")
    List<Object[]> findAllWithDriverAndTeam();
}
