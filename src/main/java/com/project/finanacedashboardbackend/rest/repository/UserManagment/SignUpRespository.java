package com.project.finanacedashboardbackend.rest.repository.UserManagment;

import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.SignUpRequestEntity;

public interface SignUpRespository {
    LoginResponseEntity addUser(SignUpRequestEntity signUpRequestEntity);
}
