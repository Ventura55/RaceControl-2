package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.domain.model.FantasyRoster;
import org.control.racecontrol.domain.model.UserWallet;
import org.control.racecontrol.domain.port.input.BuyDriverUseCase;
import org.control.racecontrol.domain.port.output.DriverRepository;
import org.control.racecontrol.domain.port.output.FantasyRosterRepository;
import org.control.racecontrol.domain.port.output.UserWalletRepository;
import org.springframework.transaction.annotation.Transactional;

public class BuyDriverService implements BuyDriverUseCase {
    private final UserWalletRepository walletRepository;
    private final FantasyRosterRepository rosterRepository;
    private final DriverRepository driverRepository;

    public BuyDriverService(UserWalletRepository walletRepository, FantasyRosterRepository rosterRepository, DriverRepository driverRepository) {
        this.walletRepository = walletRepository;
        this.rosterRepository = rosterRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional
    public void buyDriver(String username, int driverId) {
        // 1. Buscamos el driver
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalArgumentException("El piloto no existe"));

        // 2. Buscamos el equipo del usuario
        FantasyRoster roster = rosterRepository.findByUsername(username)
                .orElseGet(() -> {
                    FantasyRoster newRoster = new FantasyRoster();
                    newRoster.setUsername(username);
                    return newRoster;
                });

        if (roster.getDriverIds().size() >= 5) {
            throw new IllegalStateException("Tu equipo ya tiene 5 pilotos");
        }
        if (roster.getDriverIds().contains(driverId)) {
            throw new IllegalStateException("Ya posees a este piloto");
        }

        // 3. RECUPERAMOS EL WALLET CON BLOQUEO PESIMISTA
        UserWallet wallet = walletRepository.findByUsernameLocked(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (wallet.getBalance().compareTo(driver.getMarketValue()) < 0) {
            throw new IllegalStateException("Saldo insuficiente. Tienes " + wallet.getBalance() + " y cuesta " + driver.getMarketValue());
        }

        // 4. Cobrar y asignar
        wallet.setBalance(wallet.getBalance().subtract(driver.getMarketValue()));
        roster.getDriverIds().add(driverId);

        walletRepository.save(wallet);
        rosterRepository.save(roster);
    }
}
