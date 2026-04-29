package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.CreatePenaltyService;
import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.infrastructure.input.rest.dto.request.PenaltyRequestDto;
import org.control.racecontrol.infrastructure.input.rest.mapper.PenaltyRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/penalty")
public class CreatePenaltyController {
    @Autowired
    private CreatePenaltyService penaltyService;

    @Autowired
    private PenaltyRestMapper mapper;

    @PostMapping
    public ResponseEntity<Penalty> createPenalty(@RequestBody PenaltyRequestDto dto) {
        Penalty penaltyDomain = mapper.toDomain(dto);

        penaltyService.createPenalty(penaltyDomain);
        return ResponseEntity.ok().build();
    }
}
