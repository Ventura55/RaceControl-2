package org.control.racecontrol.infrastructure.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DriverRequestDto {
    @NotNull
    private String name;

    @NotNull
    private Long idTeam;
}
