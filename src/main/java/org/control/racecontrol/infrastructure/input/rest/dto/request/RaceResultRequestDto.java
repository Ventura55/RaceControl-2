package org.control.racecontrol.infrastructure.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.control.racecontrol.domain.model.RaceResult;

@Data
public class RaceResultRequestDto {
    @NotNull
    @Positive
    private Integer driverNumber;

    @Positive
    private Integer gridPosition;

    @Positive
    private Integer finalPosition;

    @NotNull
    private boolean fastestLap;

    @NotNull
    private RaceResult.Status status;
}
