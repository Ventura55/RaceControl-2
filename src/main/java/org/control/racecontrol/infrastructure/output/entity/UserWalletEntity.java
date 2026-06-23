package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "user_wallets")
@Data
public class UserWalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(precision = 15, scale = 2)
    private BigDecimal balance;
}