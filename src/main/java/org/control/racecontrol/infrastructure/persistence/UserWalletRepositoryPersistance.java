package org.control.racecontrol.infrastructure.persistence;

import jakarta.persistence.LockModeType;
import org.control.racecontrol.infrastructure.output.entity.UserWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserWalletRepositoryPersistance extends JpaRepository<UserWalletEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT w FROM UserWalletEntity w WHERE w.username = :username")
    Optional<UserWalletEntity> findByUsernameLocked(@Param("username") String username);
}
