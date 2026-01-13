package com.infosys.automated_test_execution_framework.controller.api;

import com.infosys.automated_test_execution_framework.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/execution")
@RequiredArgsConstructor
public class ExecutionApiController {

    private final ExecutionService service;

    @PostMapping("/run")
    public void runTests(@RequestBody List<Long> testCaseIds) {
        service.execute(testCaseIds);
    }
}
