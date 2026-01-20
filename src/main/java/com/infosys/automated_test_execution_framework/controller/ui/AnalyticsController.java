package com.infosys.automated_test_execution_framework.controller.ui;

import com.infosys.automated_test_execution_framework.repository.ExecutionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnalyticsController {

    private final ExecutionLogRepository repo;

    @GetMapping("/ui/analytics")
    public String analytics(
            @RequestParam(required = false) Long executionId,
            Model model
    ) {

        List<Object[]> summary;

        if (executionId != null) {
            summary = repo.statusSummary(executionId);
            model.addAttribute("scope", "Execution #" + executionId);
        } else {
            summary = repo.globalStatusSummary();
            model.addAttribute("scope", "All Executions");
        }

        long pass = 0;
        long fail = 0;

        for (Object[] row : summary) {
            if ("PASS".equals(row[0])) pass = (long) row[1];
            if ("FAIL".equals(row[0])) fail = (long) row[1];
        }

        model.addAttribute("pass", pass);
        model.addAttribute("fail", fail);
        model.addAttribute("total", pass + fail);

        // Individual test analytics
        model.addAttribute("perTest", repo.perTestSummary());

        return "analytics";
    }
}
