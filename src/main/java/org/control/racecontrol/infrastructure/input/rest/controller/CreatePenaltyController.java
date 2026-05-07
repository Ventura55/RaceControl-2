package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.CreatePenaltyService;
import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.infrastructure.input.rest.dto.request.PenaltyRequestDto;
import org.control.racecontrol.infrastructure.input.rest.mapper.PenaltyRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/penalty")
public class CreatePenaltyController {
    @Autowired
    private CreatePenaltyService penaltyService;

    @Autowired
    private PenaltyRestMapper mapper;

    @PostMapping("/{raceId}/results/{driverNumber}/penalties")
    public ResponseEntity<Penalty> createPenalty(@PathVariable Long raceId, @PathVariable Integer driverNumber, @RequestBody PenaltyRequestDto dto) {
        Penalty penaltyDomain = mapper.toDomain(raceId, driverNumber, dto);

        penaltyService.createPenalty(raceId, driverNumber, penaltyDomain);
        return ResponseEntity.ok().build();
    }
}
