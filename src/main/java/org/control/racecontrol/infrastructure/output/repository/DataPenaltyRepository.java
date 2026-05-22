package org.control.racecontrol.infrastructure.output.repository;

import org.control.racecontrol.infrastructure.output.entity.PenaltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataPenaltyRepository extends JpaRepository<PenaltyEntity, Long> {
    @Query("SELECT p FROM PenaltyEntity p " +
            "JOIN RaceResultEntity r ON p.idRaceResult = r.id " +
            "WHERE r.idRace = :raceId")
    List<PenaltyEntity> findByRaceId(@Param("raceId") Long raceId);
}
