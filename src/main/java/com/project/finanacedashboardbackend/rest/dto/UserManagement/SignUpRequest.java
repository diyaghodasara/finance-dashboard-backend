package com.project.finanacedashboardbackend.rest.dto.UserManagement;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
