package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.input.GetRaceResultUseCase;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetRaceResultService  implements GetRaceResultUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetRaceResultService.class);

    private RaceResultRepository resultRepository;

    public GetRaceResultService (RaceResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<RaceResult> getAll() {
        log.info("Obtenemos todos los resultados de la carrera");
        List<RaceResult> results = resultRepository.findAll();

        log.debug("Calculamos los puntos de cada jugador en la carrera");
        for (RaceResult result : results) {
            int calculatedPoints = calculatePoints(result);
            result.setPoints(calculatedPoints);
        }

        return results;
    }

    // Calculamos los puntos del piloto al vuelo no en la bd
    private int calculatePoints(RaceResult result) {

        // Si el piloto no ha terminado la carrera o ha quedado en una posicion
        // mayor a 10 obtiene 0 puntos
        if (result.getStatus() != RaceResult.Status.FINISHED || result.getFinalPosition() > 10) {
            log.info("Se devuelve 0 puntos por la posicion");
            return 0;
        }

        log.debug("calcumalos el valor de la posicion");
        // En caso de que el piloto haya quedado top 10 se le da los puntos correspondientes
        int points = switch (result.getFinalPosition()) {
            case 1 -> 25;
            case 2 -> 18;
            case 3 -> 15;
            case 4 -> 12;
            case 5 -> 10;
            case 6 -> 8;
            case 7 -> 6;
            case 8 -> 4;
            case 9 -> 2;
            case 10 -> 1;
            default -> 0;
        };

        // Si el piloto ha tenido la ultima vuelta rapida se le suma 1
        if (result.isFastestLap()) {
            log.debug("Ha tenido vuelta rapida se le suma 1 punto");
            points++;
        }

        log.info("Se devuelve los puntos calculados");
        return points;
    }

}
