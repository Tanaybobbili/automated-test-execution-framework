package com.infosys.automated_test_execution_framework.service;

import com.infosys.automated_test_execution_framework.entity.ExecutionEntity;
import com.infosys.automated_test_execution_framework.entity.ExecutionLogEntity;
import com.infosys.automated_test_execution_framework.entity.TestCaseEntity;
import com.infosys.automated_test_execution_framework.repository.ExecutionLogRepository;
import com.infosys.automated_test_execution_framework.repository.ExecutionRepository;
import com.infosys.automated_test_execution_framework.repository.TestCaseRepository;
import com.infosys.automated_test_execution_framework.service.runner.ApiTestRunner;
import com.infosys.automated_test_execution_framework.service.runner.UiTestRunner;
import com.infosys.automated_test_execution_framework.utils.Status;
import com.infosys.automated_test_execution_framework.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class ExecutionService {

    private final ExecutorService executorService;
    private final ExecutionRepository executionRepo;
    private final ExecutionLogRepository logRepo;
    private final TestCaseRepository testRepo;
    private final UiTestRunner uiRunner;
    private final ApiTestRunner apiRunner;

    public ExecutionEntity execute(List<Long> testCaseIds) {

        ExecutionEntity execution = new ExecutionEntity();
        execution.setStartedAt(TimeUtil.now());
        execution.setStatus("RUNNING");

        execution = executionRepo.save(execution);

        final Long executionId = execution.getId(); // ✅ FIX

        CountDownLatch latch = new CountDownLatch(testCaseIds.size());

        for (Long id : testCaseIds) {
            executorService.submit(() -> {
                try {
                    TestCaseEntity tc = testRepo.findById(id).orElseThrow();
                    boolean result;

                    if ("UI".equalsIgnoreCase(tc.getType())) {
                        result = uiRunner.run(tc.getTarget());
                    } else {
                        result = apiRunner.run(
                                tc.getMethod(),
                                tc.getTarget(),
                                tc.getRequestBody()
                        );
                    }

                    ExecutionLogEntity log = new ExecutionLogEntity();
                    log.setExecutionId(executionId); // ✅ SAFE
                    log.setTestCaseId(tc.getId());
                    log.setStatus(result ? Status.PASS.name() : Status.FAIL.name());
                    log.setMessage(result ? "Execution Successful" : "Execution Failed");
                    log.setExecutedAt(TimeUtil.now());

                    logRepo.save(log);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException ignored) {}

        execution.setFinishedAt(TimeUtil.now());
        execution.setStatus("COMPLETED");
        executionRepo.save(execution);

        return execution;
    }
}
