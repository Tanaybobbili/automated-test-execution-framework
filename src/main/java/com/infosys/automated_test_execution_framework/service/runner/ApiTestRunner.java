////package com.infosys.automated_test_execution_framework.service.runner;
////
////import io.restassured.RestAssured;
////import org.springframework.stereotype.Component;
////
////@Component
////public class ApiTestRunner {
////
////    public boolean run(String method, String url) {
////        try {
////            RestAssured
////                    .given()
////                    .request(method, url)
////                    .then()
////                    .statusCode(200);
////            return true;
////        } catch (Exception e) {
////            return false;
////        }
////    }
////}
//
//
//package com.infosys.automated_test_execution_framework.service.runner;
//
//import io.restassured.RestAssured;
//import io.restassured.config.SSLConfig;
//import io.restassured.response.Response;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ApiTestRunner {
//
//    public boolean run(String method, String url) {
//        try {
//            if (method == null || method.isBlank()) {
//                method = "GET"; // DEFAULT
//            }
//
//            method = method.toUpperCase().trim();
//
//            Response response = RestAssured
//                    .given()
//                    .config(RestAssured.config()
//                            .sslConfig(new SSLConfig().relaxedHTTPSValidation()))
//                    .when()
//                    .request(method, url)
//                    .then()
//                    .extract()
//                    .response();
//
//            int statusCode = response.statusCode();
//            System.out.println("API STATUS: " + statusCode);
//
//            return statusCode >= 200 && statusCode < 300;
//
//        } catch (Exception e) {
//            e.printStackTrace(); // IMPORTANT
//            return false;
//        }
//    }
//}
package com.infosys.automated_test_execution_framework.service.runner;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

@Component
public class ApiTestRunner {

    static {
        // Disable ALL default Rest-Assured filters (CRITICAL)
        RestAssured.filters();
        RestAssured.useRelaxedHTTPSValidation();
    }

    public boolean run(String method, String url, String requestBody) {
        try {
            if (method == null || method.isBlank()) {
                method = "GET";
            }

            method = method.trim().toUpperCase();

            RequestSpecification spec = RestAssured
                    .given()
                    .relaxedHTTPSValidation()
                    .redirects().follow(true)
                    .contentType("application/json");

            if (requestBody != null && !requestBody.isBlank()
                    && ("POST".equals(method) || "PUT".equals(method))) {
                spec.body(requestBody);
            }

            Response response;

            switch (method) {
                case "GET" -> response = spec.get(url);
                case "POST" -> response = spec.post(url);
                case "PUT" -> response = spec.put(url);
                case "DELETE" -> response = spec.delete(url);
                default -> throw new IllegalArgumentException("Unsupported method: " + method);
            }

            return response.getStatusCode() >= 200 && response.getStatusCode() < 300;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
