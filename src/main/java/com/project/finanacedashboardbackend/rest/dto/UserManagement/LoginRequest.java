package com.project.finanacedashboardbackend.rest.dto.UserManagement;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    //email should not be empty or null and have proper format
    @NotBlank(message = "Email must not be empty or null") //checks for both null and empty
    @Email(message = "Email should be properly formatted")
    private String email;

    //password should not be null or empty and have length >=8
    @NotBlank(message = "Password must not be empty or null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
