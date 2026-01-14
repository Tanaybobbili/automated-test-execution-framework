package com.infosys.automated_test_execution_framework.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TestCaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String target;

    private String method;

    @Column(length = 5000)
    private String requestBody;
}
