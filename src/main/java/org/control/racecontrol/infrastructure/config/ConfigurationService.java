package org.control.racecontrol.infrastructure.config;

import org.control.racecontrol.application.*;
import org.control.racecontrol.domain.port.output.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationService {

    // Con esto contruye los servicios de la aplicacion
    @Bean
    public CreateDriverService createDriverService(DriverRepository driverRepository) {
        return new CreateDriverService(driverRepository);
    }

    @Bean
    public CreatePenaltyService createPenaltyService(PenaltyRepository penaltyRepository) {
        return new CreatePenaltyService(penaltyRepository);
    }

    @Bean
    public CreateRaceService createRaceService(RaceRepository raceRepository) {
        return new CreateRaceService(raceRepository);
    }

    @Bean
    public CreateRaceResultService createRaceResultService(RaceResultRepository resultRepository) {
        return new CreateRaceResultService(resultRepository);
    }

    @Bean
    public CreateTeamService createTeamService(TeamRepository teamRepository) {
        return new CreateTeamService(teamRepository);
    }

    @Bean
    public GetRaceResultService getRaceResultService(RaceResultRepository resultRepository) {
        return new GetRaceResultService(resultRepository);
    }
}
