package com.infosys.automated_test_execution_framework.repository;

import com.infosys.automated_test_execution_framework.entity.MockResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockResourceRepository
        extends JpaRepository<MockResourceEntity, Long> {
}
