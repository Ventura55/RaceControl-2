package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.domain.model.AiAuditLog;
import org.control.racecontrol.domain.port.output.AiAuditRepository;
import org.control.racecontrol.infrastructure.output.entity.AiAuditLogEntity;
import org.control.racecontrol.infrastructure.output.mapper.AiAuditLogMapper;
import org.control.racecontrol.infrastructure.output.repository.DataAiAuditLogRepository;
import org.springframework.stereotype.Component;

@Component
public class AiAuditRepositoryAdapter implements AiAuditRepository {

    private final DataAiAuditLogRepository repository;
    private final AiAuditLogMapper mapper;

    public AiAuditRepositoryAdapter(DataAiAuditLogRepository repository, AiAuditLogMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void save(AiAuditLog auditLog) {
        repository.save(mapper.toEntity(auditLog));
    }
}
