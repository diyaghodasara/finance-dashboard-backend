package com.project.finanacedashboardbackend.rest.repository.UserManagment;

import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginRequestEntity;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginResponseEntity;

public interface LoginRepository {
    LoginResponseEntity login(LoginRequestEntity loginRequestEntity);
}
