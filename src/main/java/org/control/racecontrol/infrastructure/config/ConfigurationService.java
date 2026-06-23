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
    public CreatePenaltyService createPenaltyService(PenaltyRepository penaltyRepository, RaceResultRepository raceResultRepository, RaceEventPublisher raceEventPublisher) {
        return new CreatePenaltyService(penaltyRepository, raceResultRepository, raceEventPublisher);
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

    @Bean
    public UpdateMarketValueService updateMarketValueService(
            RaceResultRepository raceResultRepository,
            PenaltyRepository penaltyRepository,
            DriverRepository driverRepository) {
        return new UpdateMarketValueService(raceResultRepository, penaltyRepository, driverRepository);
    }

    @Bean
    public BuyDriverService buyDriverService(
            UserWalletRepository userWalletRepository,
            FantasyRosterRepository fantasyRosterRepository,
            DriverRepository driverRepository) {
        return new BuyDriverService(userWalletRepository, fantasyRosterRepository, driverRepository);
    }
}
