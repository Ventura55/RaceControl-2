package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.domain.port.input.CreateDriverUseCase;
import org.control.racecontrol.domain.port.output.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDriverService implements CreateDriverUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreateDriverService.class);

    private DriverRepository driverRepository;

    public CreateDriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public void createDriver(Driver driver) {
        log.info("Creando el piloto");

        // Contamos de que haya menos de 2 pilotos en el equipo
        long currentDrivers = driverRepository.countDriversByIdTeam(driver.getIdTeam());
        log.debug("Buscando pilotos en el equipo {}: {}", driver.getIdTeam(), currentDrivers);

        // Si hay 2 o mas que devuelva un error
        if (currentDrivers >= 2) {
            log.warn("El equipo {} no puede tener mas pilotos", driver.getIdTeam());
            throw new IllegalArgumentException("El equipo ya tiene 2 pilotos");
        }

        try {
            // Si se cumple, se guarda
            driverRepository.save(driver);
            log.info("Piloto guardado correctamente en el equipo {}", driver.getIdTeam());
        } catch (Exception e) {
            log.error("Error al intentar guardar el piloto en la bd");
            throw e;
        }
    }
}
