package org.control.racecontrol.infrastructure.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PenaltyRequestDto {
    @NotNull
    private Long id;

    @NotNull
    private Long idRaceResult;
    private Integer pentaltySeconds;
    private String reason;
}
