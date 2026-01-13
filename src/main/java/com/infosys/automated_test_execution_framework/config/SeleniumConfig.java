package com.infosys.automated_test_execution_framework.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {

    @PostConstruct
    public void setup() {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:/selenium/chromedriver.exe"
        );
    }
}
