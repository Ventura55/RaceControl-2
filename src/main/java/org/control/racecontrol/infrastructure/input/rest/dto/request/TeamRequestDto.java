package org.control.racecontrol.infrastructure.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TeamRequestDto {
    @NotNull
    @Positive
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String engineSupplier;
}
