package com.project.finanacedashboardbackend.rest.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestEntity {
    private String name;
    private String email;
    private String password;
}
