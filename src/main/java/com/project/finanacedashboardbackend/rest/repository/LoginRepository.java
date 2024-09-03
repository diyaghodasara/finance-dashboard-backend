package com.project.finanacedashboardbackend.rest.repository;

import com.project.finanacedashboardbackend.rest.entity.LoginRequestEntity;
import com.project.finanacedashboardbackend.rest.entity.LoginResponseEntity;

public interface LoginRepository {
    LoginResponseEntity login(LoginRequestEntity loginRequestEntity);
}
