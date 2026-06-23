package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.UserWallet;

import java.util.Optional;

public interface UserWalletRepository {
    Optional<UserWallet> findByUsernameLocked(String username);
    void save(UserWallet wallet);
}
