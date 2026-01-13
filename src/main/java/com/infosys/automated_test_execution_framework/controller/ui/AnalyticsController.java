package com.infosys.automated_test_execution_framework.controller.ui;

import com.infosys.automated_test_execution_framework.repository.ExecutionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnalyticsController {

    private final ExecutionLogRepository repo;

    @GetMapping("/ui/analytics")
    public String analytics(Model model) {

        List<Object[]> summary = repo.statusSummary();

        long pass = 0;
        long fail = 0;

        for (Object[] row : summary) {
            String status = (String) row[0];
            long count = (long) row[1];

            if ("PASS".equals(status)) {
                pass = count;
            } else if ("FAIL".equals(status)) {
                fail = count;
            }
        }

        model.addAttribute("pass", pass);
        model.addAttribute("fail", fail);
        model.addAttribute("total", pass + fail);

        return "analytics";
    }
}

