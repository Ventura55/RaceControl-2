package org.control.racecontrol.domain.port.output;

import org.control.racecontrol.domain.model.AiAuditLog;

public interface AiAuditRepository {
    void save(AiAuditLog auditLog);
}
