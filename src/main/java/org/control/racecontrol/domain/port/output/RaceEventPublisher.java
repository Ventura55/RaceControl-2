package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.Penalty;

public interface RaceEventPublisher {
    // Evento que se le pasa los datos de la falta
    void publishPenaltyAppliedEvent(Penalty penalty);
}
