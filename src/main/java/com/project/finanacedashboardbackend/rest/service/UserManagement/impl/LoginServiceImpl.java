package com.project.finanacedashboardbackend.rest.service.UserManagement.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailNotFoundException;
import com.project.finanacedashboardbackend.rest.config.Exception.IncorrectPasswordException;
import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginRequest;
import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginResponse;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginRequestEntity;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.mapper.entityToDto.UserManagement.LoginRequestMapper;
import com.project.finanacedashboardbackend.rest.mapper.entityToDto.UserManagement.LoginResponseMapper;
import com.project.finanacedashboardbackend.rest.repository.UserManagment.LoginRepository;
import com.project.finanacedashboardbackend.rest.service.UserManagement.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepository;
    private final LoginRequestMapper loginRequestMapper;
    private final LoginResponseMapper loginResponseMapper;
    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository, LoginRequestMapper loginRequestMapper, LoginResponseMapper loginResponseMapper) {
        this.loginRepository = loginRepository;
        this.loginRequestMapper = loginRequestMapper;
        this.loginResponseMapper = loginResponseMapper;
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try{
            LoginRequestEntity loginRequestEntity = loginRequestMapper.toEntity(loginRequest);
            LoginResponseEntity loginResponseEntity = loginRepository.login(loginRequestEntity);
            return loginResponseMapper.toDto(loginResponseEntity);
        } catch (EmailNotFoundException e){
            throw new EmailNotFoundException(e.getMessage());
        } catch (IncorrectPasswordException e){
            throw new IncorrectPasswordException(e.getMessage());
        } catch (DatabaseException e){
            throw new DatabaseException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
