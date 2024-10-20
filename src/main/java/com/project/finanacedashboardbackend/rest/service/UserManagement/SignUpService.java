package com.project.finanacedashboardbackend.rest.service.UserManagement;

import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginResponse;
import com.project.finanacedashboardbackend.rest.dto.UserManagement.SignUpRequest;

public interface SignUpService {
    LoginResponse addUser(SignUpRequest signUpRequest);
}
