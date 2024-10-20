package com.project.finanacedashboardbackend.rest.entity.UserManagement;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseEntity {
    private String token;
    private String name;
    private Integer userId;
}
