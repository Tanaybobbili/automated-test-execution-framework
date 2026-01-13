package com.infosys.automated_test_execution_framework.controller.ui;

import com.infosys.automated_test_execution_framework.entity.TestCaseEntity;
import com.infosys.automated_test_execution_framework.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final TestCaseService service;

    @GetMapping("/ui/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("tests", service.findAll());
        model.addAttribute("testCase", new TestCaseEntity());
        return "dashboard";
    }

    @PostMapping("/ui/testcase/create")
    public String createTest(@ModelAttribute TestCaseEntity testCase) {
        service.save(testCase);
        return "redirect:/ui/dashboard";
    }

}
