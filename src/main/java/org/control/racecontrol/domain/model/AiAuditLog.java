package org.control.racecontrol.domain.model;

import java.time.LocalDateTime;

public class AiAuditLog {
    private Long raceId;
    private String username;
    private LocalDateTime timestamp;
    private long executionTimeMs;
    private String status;

    public AiAuditLog(Long raceId, String username, LocalDateTime timestamp, long executionTimeMs, String status) {
        this.raceId = raceId;
        this.username = username;
        this.timestamp = timestamp;
        this.executionTimeMs = executionTimeMs;
        this.status = status;
    }

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
