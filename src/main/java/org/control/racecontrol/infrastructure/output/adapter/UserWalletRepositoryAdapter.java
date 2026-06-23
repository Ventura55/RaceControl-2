package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.UserWallet;
import org.control.racecontrol.domain.port.output.UserWalletRepository;
import org.control.racecontrol.infrastructure.output.entity.UserWalletEntity;
import org.control.racecontrol.infrastructure.persistence.UserWalletRepositoryPersistance;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserWalletRepositoryAdapter implements UserWalletRepository {

    private final UserWalletRepositoryPersistance jpaRepository;

    public UserWalletRepositoryAdapter(UserWalletRepositoryPersistance jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserWallet> findByUsernameLocked(String username) {
        return jpaRepository.findByUsernameLocked(username)
                .map(this::toDomain);
    }

    @Override
    public void save(UserWallet wallet) {
        UserWalletEntity entity = toEntity(wallet);
        jpaRepository.save(entity);
    }

    private UserWallet toDomain(UserWalletEntity entity) {
        UserWallet wallet = new UserWallet();
        wallet.setId(entity.getId());
        wallet.setUsername(entity.getUsername());
        wallet.setBalance(entity.getBalance());
        return wallet;
    }

    private UserWalletEntity toEntity(UserWallet domain) {
        UserWalletEntity entity = new UserWalletEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setBalance(domain.getBalance());
        return entity;
    }
}