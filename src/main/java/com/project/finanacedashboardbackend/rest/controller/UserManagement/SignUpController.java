package com.project.finanacedashboardbackend.rest.controller.UserManagement;

import com.project.finanacedashboardbackend.rest.dto.UserManagement.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


public interface SignUpController {
    @PostMapping("/v1/SignUp")
    ResponseEntity<?> addUser(@Valid @RequestBody SignUpRequest signUpRequest);
}
