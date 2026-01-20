package com.infosys.automated_test_execution_framework.repository;

import com.infosys.automated_test_execution_framework.entity.ExecutionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExecutionLogRepository extends JpaRepository<ExecutionLogEntity, Long> {
    List<ExecutionLogEntity> findAllByExecutionId(Long executionId);

    @Query("""
        SELECT e.status, COUNT(e)
        FROM ExecutionLogEntity e
        WHERE e.executionId = :executionId
        GROUP BY e.status
    """)
    List<Object[]> statusSummary(Long executionId);

    @Query("""
        SELECT e.status, COUNT(e)
        FROM ExecutionLogEntity e
        GROUP BY e.status
    """)
    List<Object[]> globalStatusSummary();

    @Query("""
        SELECT e.testCaseId, e.status, COUNT(e)
        FROM ExecutionLogEntity e
        GROUP BY e.testCaseId, e.status
    """)
    List<Object[]> perTestSummary();
}
