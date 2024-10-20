package com.project.finanacedashboardbackend.rest.entity.UserManagement;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestEntity {
    private String email;
    private String password;
}

