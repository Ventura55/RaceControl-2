package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "race_insights")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaceInsightEntity {

    @Id
    private Long raceId; // Usamos el ID de la carrera como clave primaria

    @Column(columnDefinition = "TEXT")
    private String insightText;

}