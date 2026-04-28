package org.control.racecontrol.infrastructure.input.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.control.racecontrol.application.CreateDriverService;
import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.infrastructure.input.rest.dto.request.DriverRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/driver")
public class CreateDriverController {
    @Autowired
    private CreateDriverService driverService;

    @PostMapping
    public ResponseEntity<Driver> saveDriver(@RequestBody DriverRequestDto driver) {
        driverService.createDriver(driver.);
        return ResponseEntity.ok().build();
    }
}
