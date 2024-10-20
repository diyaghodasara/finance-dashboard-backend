package com.project.finanacedashboardbackend.rest.service.UserManagement;

import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginRequest;
import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
