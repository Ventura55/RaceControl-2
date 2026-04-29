package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plt_penalty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plt_id")
    private long id;

    @Column(name = "rac_id")
    private long idRaceResult;

    @Column(name = "plt_penalty_seconds")
    private int seconds;

    @Column(name = "plt_reason")
    private String reason;
}
