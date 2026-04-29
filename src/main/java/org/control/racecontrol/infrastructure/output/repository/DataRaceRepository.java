package org.control.racecontrol.infrastructure.output.repository;

import org.control.racecontrol.infrastructure.output.entity.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRaceRepository extends JpaRepository<RaceEntity, Long> {
}
