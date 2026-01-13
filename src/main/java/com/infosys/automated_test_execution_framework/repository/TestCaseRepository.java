package com.infosys.automated_test_execution_framework.repository;


import com.infosys.automated_test_execution_framework.entity.TestCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCaseEntity, Long> {
}