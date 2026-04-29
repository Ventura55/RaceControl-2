package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.control.racecontrol.domain.model.RaceResult;

@Entity
@Table(name = "rac_race_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaceResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rac_id")
    private Long id;

    @Column(name = "rce_id")
    private Long idRace;

    @Column(name = "dri_id")
    private Integer idDriver;

    @Column(name = "rac_grid_position")
    private Integer gridPosition;

    @Column(name = "rac_final_position")
    private Integer finalPosition;

    @Column(name = "rac_fastest_lap")
    private Boolean fastestLap;

    @Enumerated(EnumType.STRING)
    @Column(name = "rac_status")
    private RaceResult.Status status;
}
