package com.infosys.automated_test_execution_framework.service.runner;

import io.restassured.RestAssured;
import org.springframework.stereotype.Component;

@Component
public class ApiTestRunner {

    public boolean run(String method, String url) {
        try {
            RestAssured
                    .given()
                    .request(method, url)
                    .then()
                    .statusCode(200);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}