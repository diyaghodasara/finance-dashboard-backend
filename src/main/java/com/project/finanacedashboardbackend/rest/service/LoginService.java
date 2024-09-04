package com.project.finanacedashboardbackend.rest.service;

import com.project.finanacedashboardbackend.rest.dto.LoginRequest;
import com.project.finanacedashboardbackend.rest.dto.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
