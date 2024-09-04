package com.project.finanacedashboardbackend.rest.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
