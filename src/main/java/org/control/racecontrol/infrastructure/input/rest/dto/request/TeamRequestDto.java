package org.control.racecontrol.infrastructure.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeamRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String engineSupplier;
}
