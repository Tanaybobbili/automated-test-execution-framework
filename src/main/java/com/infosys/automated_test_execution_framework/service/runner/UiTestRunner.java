package com.infosys.automated_test_execution_framework.service.runner;

import org.springframework.stereotype.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@Component
public class UiTestRunner {

    public boolean run(String url) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get(url);
            Thread.sleep(3000);
            return driver.getTitle() != null;
        } catch (Exception e) {
            return false;
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}