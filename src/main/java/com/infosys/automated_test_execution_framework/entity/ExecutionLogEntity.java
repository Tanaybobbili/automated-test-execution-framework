package com.infosys.automated_test_execution_framework.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ExecutionLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long executionId;

    private Long testCaseId;

    private String status;

    @Column(length = 1000)
    private String message;

    private LocalDateTime executedAt;

    private Long timeTakenMs;

}
