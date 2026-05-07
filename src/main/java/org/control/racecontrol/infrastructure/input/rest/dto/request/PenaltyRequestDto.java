package org.control.racecontrol.infrastructure.input.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PenaltyRequestDto {
    @Positive
    private Integer penaltySeconds;

    @NotBlank
    private String reason;
}
