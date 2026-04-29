package org.control.racecontrol.infrastructure.input.rest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RaceRequestDto {
    @NotNull
    private Long id;
    private String name;

    @Min(1)
    private Integer totalLaps;
}
