package com.project.finanacedashboardbackend.rest.controller.UserManagement;

import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginController {
    @PostMapping("/v1/Login")
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest);
}
