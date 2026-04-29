package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.CreateRaceService;
import org.control.racecontrol.domain.model.Race;
import org.control.racecontrol.infrastructure.input.rest.dto.request.RaceRequestDto;
import org.control.racecontrol.infrastructure.input.rest.mapper.RaceRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/race")
public class CreateRaceController {
    @Autowired
    private CreateRaceService raceService;

    @Autowired
    private RaceRestMapper mapper;

    @PostMapping
    public ResponseEntity<Race> createRace(@RequestBody RaceRequestDto dto) {
        Race raceDomain = mapper.toDomain(dto);

        raceService.createRace(raceDomain);
        return ResponseEntity.ok().build();
    }
}
