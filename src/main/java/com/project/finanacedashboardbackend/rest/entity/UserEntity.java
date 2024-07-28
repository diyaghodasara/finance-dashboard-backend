package com.project.finanacedashboardbackend.rest.entity;

import lombok.Data;

@Data
public class UserEntity {
    private String id;
    private String name;
    private String email;
    private String password;
}
