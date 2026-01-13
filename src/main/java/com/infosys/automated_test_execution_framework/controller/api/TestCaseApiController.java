package com.infosys.automated_test_execution_framework.controller.api;

import com.infosys.automated_test_execution_framework.entity.TestCaseEntity;
import com.infosys.automated_test_execution_framework.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testcases")
@RequiredArgsConstructor
public class TestCaseApiController {

    private final TestCaseService service;

    @PostMapping
    public TestCaseEntity create(@RequestBody TestCaseEntity tc) {
        return service.save(tc);
    }

    @GetMapping
    public List<TestCaseEntity> getAll() {
        return service.findAll();
    }
}