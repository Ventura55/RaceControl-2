package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.model.RaceResultResponse;
import org.control.racecontrol.infrastructure.output.entity.RaceResultEntity;

import java.util.List;
import java.util.Optional;

public interface RaceResultRepository {
    void save(RaceResult result);

    // Buscamos que no se repita el numero de finalPosition si
    // estan calificados
    int findFinalPositionByIdDriver(int idDriver);

    // Busco el resultado de un piloto para crear la falta
    Optional<RaceResult> findByRaceIdAndDriverNumber(long idRace, int idDriver);

    // Buscamos el resultado por id del piloto
    Optional<RaceResult> findByIdDriver(int idDriver);

    // Devolvemos una lista entera de los resultados
    List<RaceResult> findAll(long idRace);

    boolean existsByRaceIdAndFinalPositionAndStatus(long idRace, int finalPosition, RaceResult.Status status);

    List<RaceResultResponse> findAllWithDriverAndTeam();
}
