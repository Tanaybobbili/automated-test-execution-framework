package com.infosys.automated_test_execution_framework.service.runner;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApiTestRunner {

    static {
        RestAssured.useRelaxedHTTPSValidation();
    }

    public boolean run(String method, String url, String requestBody) {

        try {
            method = normalize(method);

            RequestSpecification spec = RestAssured
                    .given()
                    .contentType("application/json")
                    .accept("application/json");

            if ("POST".equals(method) || "PUT".equals(method)) {
                spec.body(requestBody != null ? requestBody : "{}");
            }

            Response response = switch (method) {
                case "GET" -> spec.get(url);
                case "POST" -> spec.post(url);
                case "PUT" -> spec.put(url);
                case "DELETE" -> spec.delete(url);
                default -> throw new IllegalArgumentException("Unsupported method");
            };

            System.out.println("API [" + method + "] " + url +
                    " -> " + response.getStatusCode());

            return response.getStatusCode() >= 200 &&
                    response.getStatusCode() < 300;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String normalize(String method) {
        return method == null || method.isBlank()
                ? "GET"
                : method.trim().toUpperCase();
    }
}
