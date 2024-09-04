package com.project.finanacedashboardbackend.rest.repository;

import com.project.finanacedashboardbackend.rest.entity.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.entity.SignUpRequestEntity;

public interface SignUpRespository {
    LoginResponseEntity addUser(SignUpRequestEntity signUpRequestEntity);
}
