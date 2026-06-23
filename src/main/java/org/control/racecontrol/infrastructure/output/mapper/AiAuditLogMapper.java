package org.control.racecontrol.infrastructure.output.mapper;

import org.control.racecontrol.domain.model.AiAuditLog;
import org.control.racecontrol.infrastructure.output.entity.AiAuditLogEntity;
import org.springframework.stereotype.Component;

@Component
public class AiAuditLogMapper {

    public AiAuditLogEntity toEntity(AiAuditLog domain) {
        return new AiAuditLogEntity(
                null,
                domain.getRaceId(),
                domain.getUsername(),
                domain.getTimestamp(),
                domain.getExecutionTimeMs(),
                domain.getStatus()
        );
    }
}
