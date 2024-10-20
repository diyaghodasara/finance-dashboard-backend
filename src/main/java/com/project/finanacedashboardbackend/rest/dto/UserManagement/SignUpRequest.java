package com.project.finanacedashboardbackend.rest.dto.UserManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    //name should not be blank or empty
    @NotBlank(message = "Name cannot be empty or null")
    private String name;

    //email should not be empty or null and have proper format
    @NotBlank(message = "Email must not be empty or null") //checks for both null and empty
    @Email(message = "Email should be properly formatted")
    private String email;

    //password should not be null or empty and have length >=8
    @NotBlank(message = "Password must not be empty or null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
