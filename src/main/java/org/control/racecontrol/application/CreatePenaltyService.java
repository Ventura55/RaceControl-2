package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.domain.port.input.CreatePenaltyUseCase;
import org.control.racecontrol.domain.port.output.PenaltyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatePenaltyService implements CreatePenaltyUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreatePenaltyService.class);

    private PenaltyRepository penaltyRepository;

    public CreatePenaltyService (PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    @Override
    public void createPenalty(Penalty penalty) {
        log.info("Iniciando la creacion de la falta");

        try {
            penaltyRepository.save(penalty);
            log.info("La falta se ha creado correctamente");
        } catch (Exception e) {
            log.error("Error no se ha podido crear correctamente en la bd");
            throw e;
        }
    }
}
