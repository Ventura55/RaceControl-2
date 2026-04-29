package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rce_race")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rce_id")
    private Long id;

    // Le indicamos el length = 100 porque en tu base de datos es un varchar(100)
    @Column(name = "rce_name", length = 100)
    private String name;

    @Column(name = "rce_total_laps")
    private Integer totalLaps;
}
