package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "fantasy_rosters")
@Data
public class FantasyRosterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @ElementCollection
    @CollectionTable(name = "roster_drivers", joinColumns = @JoinColumn(name = "roster_id"))
    @Column(name = "driver_id")
    private List<Integer> driverIds;
}