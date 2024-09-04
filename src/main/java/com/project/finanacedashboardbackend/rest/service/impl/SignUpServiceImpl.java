package com.project.finanacedashboardbackend.rest.service.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailAlreadyExistsException;
import com.project.finanacedashboardbackend.rest.dto.LoginResponse;
import com.project.finanacedashboardbackend.rest.dto.SignUpRequest;
import com.project.finanacedashboardbackend.rest.entity.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.entity.SignUpRequestEntity;
import com.project.finanacedashboardbackend.rest.mapper.entityToDto.LoginResponseMapper;
import com.project.finanacedashboardbackend.rest.mapper.entityToDto.SignUpRequestMapper;
import com.project.finanacedashboardbackend.rest.repository.SignUpRespository;
import com.project.finanacedashboardbackend.rest.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final SignUpRespository signUpRespository;
    private final SignUpRequestMapper signUpRequestMapper;
    private final LoginResponseMapper loginResponseMapper;
    @Autowired
    public SignUpServiceImpl(SignUpRespository signUpRespository,SignUpRequestMapper signUpRequestMapper,LoginResponseMapper loginResponseMapper) {
        this.signUpRespository = signUpRespository;
        this.signUpRequestMapper = signUpRequestMapper;
        this.loginResponseMapper = loginResponseMapper;
    }
    @Override
    public LoginResponse addUser(SignUpRequest signUpRequest) {
        try{
            SignUpRequestEntity signUpRequestEntity = signUpRequestMapper.toEntity(signUpRequest);
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
