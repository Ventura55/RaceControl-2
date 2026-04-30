package org.control.racecontrol.infrastructure.input.rest.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.control.racecontrol.domain.model.RaceResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaceResultResponseDto {
    @NotNull
    @Positive
    private Integer idDriver;

    private String nameDriver;
    private String nameTeam;

    @Min(0)
    private Integer points;
}
