package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.model.RaceResultResponse;
import org.control.racecontrol.domain.port.input.GetRaceResultUseCase;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRaceResultService  implements GetRaceResultUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetRaceResultService.class);

    private RaceResultRepository resultRepository;

    public GetRaceResultService (RaceResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<RaceResultResponse> getAll() {
        log.info("Obteniendo resultados completos para la clasificación");
        List<RaceResultResponse> allResults = resultRepository.findAllWithDriverAndTeam();

        Map<Integer, RaceResultResponse> standingsMap = new HashMap<>();

        for (RaceResultResponse response : allResults) {
            int points = calculatePoints(response.getRaceResult());
            int driverId = response.getRaceResult().getIdDriver();

            if (standingsMap.containsKey(driverId)) {
                RaceResult existingResult = standingsMap.get(driverId).getRaceResult();
                existingResult.setPoints(existingResult.getPoints() + points);
            } else {
                RaceResult aggregatedResult = new RaceResult();
                aggregatedResult.setIdDriver(driverId);
                aggregatedResult.setPoints(points);

                standingsMap.put(driverId, new RaceResultResponse(
                        aggregatedResult,
                        response.getDriver(),
                        response.getTeam()
                ));
            }
        }

        log.info("Ordenando la clasificación por puntos de mayor a menor");
        return standingsMap.values().stream()
                .sorted((r1, r2) -> Integer.compare(r2.getRaceResult().getPoints(), r1.getRaceResult().getPoints()))
                .toList();
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
