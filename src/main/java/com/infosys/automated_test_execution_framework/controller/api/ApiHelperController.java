package com.infosys.automated_test_execution_framework.controller.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/helper")
public class ApiHelperController {

    @GetMapping("/fetch")
    public String fetch(@RequestParam String url) {
        Response response = RestAssured.given().get(url);
        return response.asPrettyString();
    }
}