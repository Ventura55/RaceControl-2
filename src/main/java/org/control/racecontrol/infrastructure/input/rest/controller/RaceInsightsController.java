package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.GenerateRaceInsightsService;
import org.control.racecontrol.infrastructure.input.rest.dto.response.RaceInsightResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races")
public class RaceInsightsController {
    private final GenerateRaceInsightsService generateRaceInsightsService;

    public RaceInsightsController(GenerateRaceInsightsService generateRaceInsightsService) {
        this.generateRaceInsightsService = generateRaceInsightsService;
    }

    @GetMapping("/{raceId}/insights")
    public ResponseEntity<RaceInsightResponseDto> getRaceInsights(@PathVariable Long raceId) {
        String report = generateRaceInsightsService.execute(raceId);
        if (report == null || report.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(new RaceInsightResponseDto(raceId, "El reporte de la carrera aún se está generando..."));
        }

        return ResponseEntity.ok(new RaceInsightResponseDto(raceId, report));
    }
}
