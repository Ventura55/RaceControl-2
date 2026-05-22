package org.control.racecontrol.infrastructure.input.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaceInsightResponseDto {
    private Long raceId;
    private String generatedReport;
}
