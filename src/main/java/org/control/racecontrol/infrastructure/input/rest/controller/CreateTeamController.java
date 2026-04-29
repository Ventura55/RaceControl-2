package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.application.CreateTeamService;
import org.control.racecontrol.domain.model.Team;
import org.control.racecontrol.infrastructure.input.rest.dto.request.TeamRequestDto;
import org.control.racecontrol.infrastructure.input.rest.mapper.TeamRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class CreateTeamController {
    @Autowired
    private CreateTeamService teamService;

    @Autowired
    private TeamRestMapper mapper;

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody TeamRequestDto dto) {
        Team teamDomain = mapper.toDomain(dto);

        teamService.createExecute(teamDomain);
        return ResponseEntity.ok().build();
    }
}
