package com.project.finanacedashboardbackend.rest.controller;

import com.project.finanacedashboardbackend.rest.dto.LoginResponse;
import com.project.finanacedashboardbackend.rest.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface SignUpController {
    @PostMapping("/v1/SignUp")
    ResponseEntity<?> addUser(@RequestBody SignUpRequest signUpRequest);
}
