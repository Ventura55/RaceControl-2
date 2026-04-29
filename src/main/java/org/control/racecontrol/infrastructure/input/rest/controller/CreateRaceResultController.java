package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.CreateRaceResultService;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.infrastructure.input.rest.dto.request.RaceResultRequestDto;
import org.control.racecontrol.infrastructure.input.rest.mapper.RaceResultRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raceResult")
public class CreateRaceResultController {
    @Autowired
    private CreateRaceResultService raceResultService;

    @Autowired
    private RaceResultRestMapper mapper;

    @PostMapping
    public ResponseEntity<RaceResult> createRaceResult(@RequestBody RaceResultRequestDto dto) {
        RaceResult resultDomain = mapper.toDomain(dto);

        raceResultService.createRaceResult(resultDomain);
        return ResponseEntity.ok().build();
    }
}
