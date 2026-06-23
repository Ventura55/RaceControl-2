package org.control.racecontrol.infrastructure.input.rest.mapper;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.infrastructure.input.rest.dto.request.DriverRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DriverRestMapper {
    public Driver toDomain(DriverRequestDto dto) {
        if (dto == null) return null;

        Driver driver = new Driver();
        driver.setName(dto.getName());
        driver.setIdTeam(dto.getIdTeam());

        return driver;
    }
}
