package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.GetRaceResultService;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.model.RaceResultResponse;
import org.control.racecontrol.infrastructure.input.rest.dto.response.RaceResultResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/get/result")
public class GetRaceResultController {
    @Autowired
    private GetRaceResultService raceResultService;

    @GetMapping("/drivers")
    public ResponseEntity<List<RaceResultResponseDto>> listRaceResult() {
        List<RaceResultResponse> standings = raceResultService.getAll();

        List<RaceResultResponseDto> dtos = standings.stream().map(response -> new RaceResultResponseDto(
                response.getRaceResult().getIdDriver(),
                response.getDriver().getName(),
                response.getTeam().getName(),
                response.getRaceResult().getPoints()
        )).toList();

        return ResponseEntity.ok(dtos);
    }
}
