package com.project.finanacedashboardbackend.rest.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Transactions {
    private String id;
    private LocalDate date;
    private Double amount;
    private String type;
    private Long account_number;
    private String category;
}
