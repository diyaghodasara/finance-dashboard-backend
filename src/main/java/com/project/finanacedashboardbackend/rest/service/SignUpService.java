package com.project.finanacedashboardbackend.rest.service;

import com.project.finanacedashboardbackend.rest.dto.LoginResponse;
import com.project.finanacedashboardbackend.rest.dto.SignUpRequest;

public interface SignUpService {
    LoginResponse addUser(SignUpRequest signUpRequest);
}
