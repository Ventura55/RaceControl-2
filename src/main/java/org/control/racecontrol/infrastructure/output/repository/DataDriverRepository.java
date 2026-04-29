package org.control.racecontrol.infrastructure.output.repository;

import org.control.racecontrol.infrastructure.output.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDriverRepository extends JpaRepository<DriverEntity, Integer> {
    long countByIdTeam(long idTeam);
}
