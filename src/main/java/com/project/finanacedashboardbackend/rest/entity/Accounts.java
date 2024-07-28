package com.project.finanacedashboardbackend.rest.entity;

import lombok.Data;

@Data
public class Accounts {
    private Long id;
    private String userId;
    private String type;
    private Double balance;
    private String bankName;
}
