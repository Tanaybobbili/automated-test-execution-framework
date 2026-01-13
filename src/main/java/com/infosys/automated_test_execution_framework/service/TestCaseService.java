package com.infosys.automated_test_execution_framework.service;

import com.infosys.automated_test_execution_framework.entity.TestCaseEntity;
import com.infosys.automated_test_execution_framework.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository repository;

    public TestCaseEntity save(TestCaseEntity tc) {
        return repository.save(tc);
    }

    public List<TestCaseEntity> findAll() {
        return repository.findAll();
    }

    public TestCaseEntity findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
