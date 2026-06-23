package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dri_driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dri_id")
    private int id;

    @Column(name = "dri_name")
    private String name;

    @Column(name = "tea_id")
    private long idTeam;

    // Añade esto a tus otros campos en DriverEntity
    @Column(precision = 15, scale = 2)
    private java.math.BigDecimal marketValue;
}
