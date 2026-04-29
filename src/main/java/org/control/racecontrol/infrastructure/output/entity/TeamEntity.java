package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tea_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tea_id")
    private Long id;

    @Column(name = "tea_name", length = 100)
    private String name;

    @Column(name = "tea_engine_supplier")
    private String engineSupplier;
}