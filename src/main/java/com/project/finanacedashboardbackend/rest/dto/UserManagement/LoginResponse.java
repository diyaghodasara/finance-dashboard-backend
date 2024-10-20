package com.project.finanacedashboardbackend.rest.dto.UserManagement;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String name;
    private Integer userId;
}
