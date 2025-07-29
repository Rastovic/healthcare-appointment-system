package com.main.app.Services;

import com.main.app.Model.AuditLog;
import com.main.app.Repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;





    public void logAction(String action, String performedBy) {

        AuditLog log = new AuditLog();

        log.setAction(action);

        log.setPerformedBy(performedBy);

        log.setTimestamp(java.time.LocalDateTime.now());

        auditLogRepository.save(log);

    }

    public List<AuditLog> findAll() {
    return auditLogRepository.findAll();
    }
}