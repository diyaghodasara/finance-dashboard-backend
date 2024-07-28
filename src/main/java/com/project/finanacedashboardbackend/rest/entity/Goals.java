package com.project.finanacedashboardbackend.rest.entity;

import lombok.Data;

@Data
public class Goals {
    private Long id;
    private String name;
    private String description;
    private double goalAmount;
    private double savedAmount;
    private String userId;
}
