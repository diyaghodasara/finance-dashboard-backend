package com.project.finanacedashboardbackend.rest.service.UserManagement.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailAlreadyExistsException;
import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginResponse;
import com.project.finanacedashboardbackend.rest.dto.UserManagement.SignUpRequest;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.SignUpRequestEntity;
import com.project.finanacedashboardbackend.rest.mapper.entityToDto.UserManagement.LoginResponseMapper;
import com.project.finanacedashboardbackend.rest.mapper.entityToDto.UserManagement.SignUpRequestMapper;
import com.project.finanacedashboardbackend.rest.repository.UserManagment.SignUpRespository;
import com.project.finanacedashboardbackend.rest.service.UserManagement.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final SignUpRespository signUpRespository;
    private final SignUpRequestMapper signUpRequestMapper;
    private final LoginResponseMapper loginResponseMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public SignUpServiceImpl(SignUpRespository signUpRespository,SignUpRequestMapper signUpRequestMapper,LoginResponseMapper loginResponseMapper,
                             PasswordEncoder passwordEncoder) {
        this.signUpRespository = signUpRespository;
        this.signUpRequestMapper = signUpRequestMapper;
        this.loginResponseMapper = loginResponseMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public LoginResponse addUser(SignUpRequest signUpRequest) {
        try{
            SignUpRequestEntity signUpRequestEntity = signUpRequestMapper.toEntity(signUpRequest);
            String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
            signUpRequestEntity.setHashedPassword(hashedPassword);
            LoginResponseEntity loginResponseEntity = signUpRespository.addUser(signUpRequestEntity);
            return loginResponseMapper.toDto(loginResponseEntity);
        } catch (EmailAlreadyExistsException e) {
            throw new EmailAlreadyExistsException(e.getMessage());
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
