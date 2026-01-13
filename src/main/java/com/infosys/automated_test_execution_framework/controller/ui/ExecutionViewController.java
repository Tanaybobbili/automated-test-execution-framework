package com.infosys.automated_test_execution_framework.controller.ui;

import com.infosys.automated_test_execution_framework.repository.ExecutionLogRepository;
import com.infosys.automated_test_execution_framework.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExecutionViewController {

    private final ExecutionLogRepository logRepository;
    private final ExecutionService executionService;

    @GetMapping("/ui/executions")
    public String viewExecutions(Model model) {
        model.addAttribute("logs", logRepository.findAll());
        return "executions";
    }

    @PostMapping("/ui/execute")
    public String executeFromUI(@RequestParam("testIds") List<Long> ids) {
        executionService.execute(ids);
        return "redirect:/ui/executions";
    }

}