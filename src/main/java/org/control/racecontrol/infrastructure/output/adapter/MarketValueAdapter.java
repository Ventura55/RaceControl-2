package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.port.input.UpdateMarketValueUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketValueAdapter {

    private final UpdateMarketValueUseCase updateMarketValueUseCase;

    public MarketValueAdapter(UpdateMarketValueUseCase updateMarketValueUseCase) {
        this.updateMarketValueUseCase = updateMarketValueUseCase;
    }

    // Se ejecuta a las 02:00 AM todos los lunes
    @Scheduled(cron = "0 0 2 * * MON")
    public void executeMarketValueUpdate() {
        System.out.println("⏳ [CRON JOB] Iniciando actualización de la Bolsa de Valores F1...");

        // Aquí idealmente buscarías la última carrera en la BD. Usaremos la 1 como ejemplo
        long lastRaceId = 1L;
        updateMarketValueUseCase.calculateAndApplyNewValues(lastRaceId);

        System.out.println("✅ [CRON JOB] Valores de mercado actualizados.");
    }
}