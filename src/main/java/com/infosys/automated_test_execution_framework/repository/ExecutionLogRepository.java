package com.infosys.automated_test_execution_framework.repository;

import com.infosys.automated_test_execution_framework.entity.ExecutionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExecutionLogRepository extends JpaRepository<ExecutionLogEntity, Long> {
    @Query("SELECT e.status, COUNT(e) FROM ExecutionLogEntity e GROUP BY e.status")
    List<Object[]> statusSummary();
}
