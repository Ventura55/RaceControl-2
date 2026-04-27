package org.control.racecontrol.domain.port.input;

import org.control.racecontrol.domain.model.Driver;

public interface CreateDriverUseCase {
    // Nos llaman desde afuera diciendo que tenemos que añadir un
    // piloto a un equipo
    void createDriver(Driver driver);
}
