package org.control.racecontrol.infrastructure.output.repository;

import org.control.racecontrol.infrastructure.output.entity.RaceResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRaceResultRepository extends JpaRepository<RaceResultEntity, Long> {
}
