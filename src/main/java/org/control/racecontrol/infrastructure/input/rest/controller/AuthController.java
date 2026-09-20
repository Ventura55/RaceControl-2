package org.control.racecontrol.infrastructure.input.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Validación provisional (Sustituir por AuthenticationManager / UserDetailsService)
        if ("user".equals(username) && "1234".equals(password)) {
            return ResponseEntity.ok(Map.of(
                    "message", "Login exitoso",
                    "token", "fake-jwt-token-demo-123456"
            ));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Usuario o contraseña incorrectos"));
    }
}