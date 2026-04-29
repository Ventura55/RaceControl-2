package org.control.racecontrol.infrastructure.output.repository;

import org.control.racecontrol.infrastructure.output.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTeamRepository extends JpaRepository<TeamEntity, Long> {
}
