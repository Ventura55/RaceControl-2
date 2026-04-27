package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Penalty;
import org.control.racecontrol.domain.port.input.CreatePenaltyUseCase;
import org.control.racecontrol.domain.port.output.PenaltyRepository;

public class CreatePenaltyService implements CreatePenaltyUseCase {
    private PenaltyRepository penaltyRepository;

    public CreatePenaltyService (PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    @Override
    public void createPenalty(Penalty penalty) {
        penaltyRepository.save(penalty);
    }
}
