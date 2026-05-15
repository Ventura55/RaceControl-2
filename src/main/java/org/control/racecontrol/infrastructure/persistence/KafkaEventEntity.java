package org.control.racecontrol.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "outbox_events")
@Data
public class KafkaEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String topic;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private String status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime processedAt;
}
