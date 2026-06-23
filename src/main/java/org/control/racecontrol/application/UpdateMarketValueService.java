package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.domain.model.RaceResult;
import org.control.racecontrol.domain.port.input.UpdateMarketValueUseCase;
import org.control.racecontrol.domain.port.output.DriverRepository;
import org.control.racecontrol.domain.port.output.PenaltyRepository;
import org.control.racecontrol.domain.port.output.RaceResultRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public class UpdateMarketValueService implements UpdateMarketValueUseCase {
    private final RaceResultRepository raceResultRepository;
    private final PenaltyRepository penaltyRepository;
    private final DriverRepository driverRepository;

    public UpdateMarketValueService(RaceResultRepository raceResultRepository, PenaltyRepository penaltyRepository, DriverRepository driverRepository) {
        this.raceResultRepository = raceResultRepository;
        this.penaltyRepository = penaltyRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional
    public void calculateAndApplyNewValues(long raceId) {
        List<RaceResult> results = raceResultRepository.findAll(raceId);

        for (RaceResult result : results) {
            Driver driver = driverRepository.findById(result.getIdDriver()).orElseThrow();
            BigDecimal currentValue = driver.getMarketValue();
            if(currentValue == null) currentValue = new BigDecimal("1000000"); // Valor base si es null

            // Sube 1% por punto
            int points = result.getPoints();
            BigDecimal pointBonus = currentValue.multiply(new BigDecimal("0.01")).multiply(new BigDecimal(points));

            // Baja 5% por cada penalización en esa carrera
            long penaltyCount = penaltyRepository.countByRaceResultId(result.getId());
            BigDecimal penaltyDeduction = currentValue.multiply(new BigDecimal("0.05")).multiply(new BigDecimal(penaltyCount));

            // Aplicar formula
            BigDecimal newValue = currentValue.add(pointBonus).subtract(penaltyDeduction);
            driver.setMarketValue(newValue);

            driverRepository.save(driver);
        }
    }
}
