package com.infosys.automated_test_execution_framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    @Bean(destroyMethod = "shutdown")
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(5);
    }
}
