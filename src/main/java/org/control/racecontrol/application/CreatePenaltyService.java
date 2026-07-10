package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.input.CreatePenaltyUseCase;
import org.control.racecontrol.domain.port.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class CreatePenaltyService implements CreatePenaltyUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreatePenaltyService.class);

    private final PenaltyRepository penaltyRepository;
    private final RaceResultRepository raceResultRepository;
    private final RaceEventPublisher eventPublisher;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    public CreatePenaltyService (PenaltyRepository penaltyRepository, RaceResultRepository raceResultRepository, RaceEventPublisher eventPublisher, DriverRepository driverRepository, UserRepository userRepository) {
        this.penaltyRepository = penaltyRepository;
        this.raceResultRepository = raceResultRepository;
        this.eventPublisher = eventPublisher;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    }

    public void createPenalty(Long raceId, Integer driverNumber, Penalty penalty) {
        log.info("Iniciando la creacion de la falta para el piloto {} en la carrera {}", driverNumber, raceId);

        // 1. Obtener el usuario/manager que está haciendo la petición desde el contexto de Spring Security
        String currentManager = SecurityContextHolder.getContext().getAuthentication().getName();
        String currentUsername = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();

        // B. Buscamos el piloto en la BD usando el método findById de tu DriverRepository
        Driver driver = driverRepository.findById(driverNumber.longValue())
                .orElseThrow(() -> new IllegalArgumentException("El piloto especificado no existe en la parrilla"));

        Long driverTeam = driver.getIdTeam();
        Long managerTeam = teamOfUser(currentUsername);

        // C. Simulamos la comprobación de escuderías.
        // (En un entorno real, cruzarías el ID de escudería del mánager con driver.getIdTeam())
        log.info("Verificando si el manager [{}] tiene permisos sobre el equipo del piloto [{}]", currentManager, driver.getName());

        boolean isOwnerOfTeam = checkManagerPermission(currentManager, driver);


        if (!isOwnerOfTeam) {
            log.error("🚨 ALERTA DE SEGURIDAD: El manager [{}] intentó sancionar ilegalmente al piloto rival [{}]", currentManager, driver.getName());
            throw new AccessDeniedException("Operación rechazada: No puedes aplicar sanciones a pilotos de escuderías rivales.");
        }
        if (!managerTeam.equals(driverTeam)) {
            log.error("🚨 Intento de sabotaje: El manager {} intentó sancionar al piloto rival {}", currentUsername, driverNumber);
            throw new org.springframework.security.access.AccessDeniedException(
                    "No tienes autorización para gestionar penalizaciones de pilotos de otras escuderías."
            );
        }

        Optional<RaceResult> result = raceResultRepository.findByRaceIdAndDriverNumber(raceId, driverNumber);

        if (result.isEmpty()) {
            log.warn("No se puede aplicar penalización: El piloto no tiene resultados en esta carrera");
            throw new IllegalArgumentException("No existe un registro de carrera para este piloto");
        }

        try {
            penalty.setIdRaceResult(result.get().getId());

            penaltyRepository.save(penalty);
            eventPublisher.publishPenaltyAppliedEvent(penalty);
            log.info("La falta se ha creado correctamente vinculada al resultado ID: {}", penalty.getIdRaceResult());
        } catch (Exception e) {
            log.error("Error: No se ha podido guardar la penalización en la BD", e);
            throw e;
        }
    }

    private boolean checkManagerPermission(String managerUsername, Driver driver) {
        // En producción, aquí verificarías si el manager gestiona el mismo idTeam del piloto.
        // Si el usuario es un "STEWARD" (Comisario de la FIA), siempre devuelve true.
        return true;
    }

    private Long teamOfUser(String currentUsername) {
        return userRepository.findTeamIdByUsername(currentUsername)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException(
                        "El usuario no está asignado a ninguna escudería oficial."
                ));
    }

    @Override
    public void createPenalty(Penalty penalty) {
        log.info("Iniciando la creacion de la falta");

        try {
            penaltyRepository.save(penalty);
            eventPublisher.publishPenaltyAppliedEvent(penalty);
            log.info("La falta se ha creado correctamente");
        } catch (Exception e) {
            log.error("Error no se ha podido crear correctamente en la bd");
            throw e;
        }
    }
}
