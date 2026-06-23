package org.control.racecontrol.infrastructure.persistence;

import org.control.racecontrol.infrastructure.output.entity.FantasyRosterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FantasyRosterRepositoryPersistence extends JpaRepository<FantasyRosterEntity, Long> {
    Optional<FantasyRosterEntity> findByUsername(String username);
}
