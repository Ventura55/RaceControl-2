package org.control.racecontrol.domain.port.input;

public interface UpdateMarketValueUseCase {
    void calculateAndApplyNewValues(long raceId);
}
