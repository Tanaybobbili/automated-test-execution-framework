package com.infosys.automated_test_execution_framework.service;

import com.infosys.automated_test_execution_framework.entity.ExecutionLogEntity;
import com.infosys.automated_test_execution_framework.entity.TestCaseEntity;
import com.infosys.automated_test_execution_framework.repository.ExecutionLogRepository;
import com.infosys.automated_test_execution_framework.repository.TestCaseRepository;
import com.infosys.automated_test_execution_framework.service.runner.ApiTestRunner;
import com.infosys.automated_test_execution_framework.service.runner.UiTestRunner;
import com.infosys.automated_test_execution_framework.utils.Status;
import com.infosys.automated_test_execution_framework.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class ExecutionService {

    private final ExecutorService executorService;
    private final TestCaseRepository testRepo;
    private final ExecutionLogRepository logRepo;
    private final UiTestRunner uiRunner;
    private final ApiTestRunner apiRunner;

    public void execute(List<Long> testCaseIds) {

        for (Long id : testCaseIds) {
            executorService.submit(() -> {

                TestCaseEntity tc = testRepo.findById(id).orElseThrow();
                boolean result;

                if ("UI".equalsIgnoreCase(tc.getType())) {
                    result = uiRunner.run(tc.getTarget());
                } else {
                    result = apiRunner.run(tc.getMethod(), tc.getTarget());
                }

                ExecutionLogEntity log = new ExecutionLogEntity();
                log.setTestCaseId(tc.getId());
                log.setStatus(result ? Status.PASS.name() : Status.FAIL.name());
                log.setMessage(result ? "Execution Successful" : "Execution Failed");
                log.setExecutedAt(TimeUtil.now());

                logRepo.save(log);
            });
        }
    }
}
