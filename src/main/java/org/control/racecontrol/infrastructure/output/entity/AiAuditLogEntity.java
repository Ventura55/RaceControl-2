package org.control.racecontrol.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ai_audit_logs")
@Data
@AllArgsConstructor @NoArgsConstructor
public class AiAuditLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long raceId;
    private String username;
    private LocalDateTime requestedAt;
    private long executionTimeMs;
    private String status;
    
}
