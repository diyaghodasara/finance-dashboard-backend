package com.project.finanacedashboardbackend.rest.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String firstName;
    private String userId;
}
