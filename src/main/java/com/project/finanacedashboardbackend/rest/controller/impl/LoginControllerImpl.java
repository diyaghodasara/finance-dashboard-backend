package com.project.finanacedashboardbackend.rest.controller.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailNotFoundException;
import com.project.finanacedashboardbackend.rest.config.Exception.IncorrectPasswordException;
import com.project.finanacedashboardbackend.rest.controller.LoginController;
import com.project.finanacedashboardbackend.rest.dto.ErrorResponse;
import com.project.finanacedashboardbackend.rest.dto.LoginRequest;
import com.project.finanacedashboardbackend.rest.dto.LoginResponse;
import com.project.finanacedashboardbackend.rest.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginControllerImpl implements LoginController {
    private final LoginService loginService;
    public LoginControllerImpl(LoginService loginService) {
        this.loginService = loginService;
    }
    @Override
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try{
            LoginResponse loginResponse = loginService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (EmailNotFoundException | IncorrectPasswordException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
