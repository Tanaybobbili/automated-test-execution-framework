package com.infosys.automated_test_execution_framework.repository;

import com.infosys.automated_test_execution_framework.entity.ExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<ExecutionEntity, Long> {
}
