package com.main.app.Repositories;

import com.main.app.Model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
