package org.control.racecontrol.infrastructure.output.repository;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.infrastructure.output.entity.RaceResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRaceResultRepository extends JpaRepository<RaceResultEntity, Long> {
    Optional<RaceResult> findByIdDriver(long idDriver);

    List<RaceResult> findAllByIdRace(long idRace);

    boolean existsByIdRaceAndFinalPositionAndStatus(Long idRace, Integer finalPosition, RaceResult.Status status);
}
