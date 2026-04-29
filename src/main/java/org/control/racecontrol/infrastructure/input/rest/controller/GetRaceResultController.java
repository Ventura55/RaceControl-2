package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.GetRaceResultService;
import org.control.racecontrol.domain.model.RaceResult;
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

    @GetMapping
    public ResponseEntity<List<RaceResultResponseDto>> listRaceResult() {
        List<RaceResult> results = raceResultService.getAll();
        List<RaceResultResponseDto> dtos = results.stream().map(result -> new RaceResultResponseDto(
                result.getIdDriver(),
        ));
    }
}
