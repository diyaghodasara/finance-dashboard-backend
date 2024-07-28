package com.project.finanacedashboardbackend.rest.entity;

import lombok.Data;

@Data
public class Transactions {
    private String id;
    private String date;
    private Double amount;
    private String type;
    private Long account_number;
    private String category;
}
