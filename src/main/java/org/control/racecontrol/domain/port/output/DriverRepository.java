package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository {
    // Una vez nos hayan hecho la llamada contamos que no
    // haya dos pilotos en un equipo
    long countDriversByIdTeam(long idTeam);

    // Bucamos el piloto por Id
    Optional<Driver> findById(long driverId);

    // Guardamos el nuevo piloto
    void save(Driver driver);
}
