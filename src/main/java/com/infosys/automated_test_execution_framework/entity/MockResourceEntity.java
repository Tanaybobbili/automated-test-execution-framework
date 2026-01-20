package com.infosys.automated_test_execution_framework.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MockResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
