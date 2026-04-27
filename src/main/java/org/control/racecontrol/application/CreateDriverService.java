package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.domain.port.input.CreateDriverUseCase;
import org.control.racecontrol.domain.port.output.DriverRepository;

public class CreateDriverService implements CreateDriverUseCase {
    private DriverRepository driverRepository;

    public CreateDriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public void createDriver(Driver driver) {
        // Contamos de que haya menos de 2 pilotos en el equipo
        long currentDrivers = driverRepository.countDriversByIdTeam(driver.getIdTeam());

        // Si hay 2 o mas que devuelva un error
        if (currentDrivers >= 2) throw new IllegalArgumentException("El equipo ya tiene 2 pilotos");

        // Si se cumple, se guarda
        driverRepository.save(driver);
    }
}
