package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.CreateRaceResultService;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.infrastructure.input.rest.dto.request.RaceResultRequestDto;
import org.control.racecontrol.infrastructure.input.rest.mapper.RaceResultRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/raceResult")
public class CreateRaceResultController {
    @Autowired
    private CreateRaceResultService raceResultService;

    @Autowired
    private RaceResultRestMapper mapper;

    @PostMapping("/{raceId}/results")
    public ResponseEntity<?> createRaceResult(@PathVariable Long raceId, @RequestBody RaceResultRequestDto dto) {
        try {
            RaceResult resultDomain = mapper.toDomain(raceId, dto);

            raceResultService.createRaceResult(resultDomain);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
