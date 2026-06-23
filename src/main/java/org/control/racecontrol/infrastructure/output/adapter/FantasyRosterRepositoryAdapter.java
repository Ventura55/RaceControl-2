package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.FantasyRoster;
import org.control.racecontrol.domain.port.output.FantasyRosterRepository;
import org.control.racecontrol.infrastructure.output.entity.FantasyRosterEntity;
import org.control.racecontrol.infrastructure.persistence.FantasyRosterRepositoryPersistence;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class FantasyRosterRepositoryAdapter implements FantasyRosterRepository {
    private final FantasyRosterRepositoryPersistence jpaRepository;

    public FantasyRosterRepositoryAdapter(FantasyRosterRepositoryPersistence jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<FantasyRoster> findByUsername(String username) {
        return jpaRepository.findByUsername(username)
                .map(this::toDomain);
    }

    @Override
    public void save(FantasyRoster roster) {
        FantasyRosterEntity entity = toEntity(roster);
        jpaRepository.save(entity);
    }

    private FantasyRoster toDomain(FantasyRosterEntity entity) {
        FantasyRoster roster = new FantasyRoster();
        roster.setId(entity.getId());
        roster.setUsername(entity.getUsername());
        roster.setDriverIds(new ArrayList<>(entity.getDriverIds()));
        return roster;
    }

    private FantasyRosterEntity toEntity(FantasyRoster domain) {
        FantasyRosterEntity entity = new FantasyRosterEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setDriverIds(new ArrayList<>(domain.getDriverIds()));
        return entity;
    }
}
