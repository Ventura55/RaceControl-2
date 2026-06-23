package org.control.racecontrol.infrastructure.input.rest.controller;

import org.control.racecontrol.domain.port.input.BuyDriverUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fantasy/roster")
public class FantasyController {

    private final BuyDriverUseCase buyDriverUseCase;

    public FantasyController(BuyDriverUseCase buyDriverUseCase) {
        this.buyDriverUseCase = buyDriverUseCase;
    }

    @PostMapping("/buy/{driverId}")
    public ResponseEntity<String> buyDriver(@PathVariable int driverId, @RequestParam String username) {
        try {
            buyDriverUseCase.buyDriver(username, driverId);
            return ResponseEntity.ok("Piloto comprado con éxito");
        } catch (IllegalStateException e) {
            // Devuelve 422 si no hay saldo o reglas de negocio fallan
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}