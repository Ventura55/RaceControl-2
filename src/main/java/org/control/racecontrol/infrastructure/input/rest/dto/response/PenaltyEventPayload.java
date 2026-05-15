package org.control.racecontrol.infrastructure.input.rest.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyEventPayload {
    @NotNull
    @Positive
    private Long driverId;
    private int penaltySeconds;
    private String reason;
}
