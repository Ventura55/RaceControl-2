package org.control.racecontrol.infrastructure.output.mapper;

import org.control.racecontrol.domain.model.Race;
import org.control.racecontrol.infrastructure.output.entity.RaceEntity;
import org.springframework.stereotype.Component;

@Component
public class RacePersistenceMapper {
    public RaceEntity toEntity(Race race) {
        if (race == null) return null;

        RaceEntity entity = new RaceEntity();
        entity.setName(race.getName());
        entity.setTotalLaps(race.getTotalLaps());
        return entity;
    }

    public Race toDomain(RaceEntity entity) {
        if (entity == null) return null;

        Race race = new Race();
        race.setId(entity.getId());
        race.setName(entity.getName());
        race.setTotalLaps(entity.getTotalLaps());

        return race;
    }
}
