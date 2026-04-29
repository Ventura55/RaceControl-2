package org.control.racecontrol.infrastructure.output.repository;

import org.control.racecontrol.infrastructure.output.entity.PenaltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPenaltyRepository extends JpaRepository<PenaltyEntity, Long> {
}
