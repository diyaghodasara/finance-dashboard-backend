package com.project.finanacedashboardbackend.rest.controller.UserManagement.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailAlreadyExistsException;
import com.project.finanacedashboardbackend.rest.controller.UserManagement.SignUpController;
import com.project.finanacedashboardbackend.rest.dto.ErrorResponse;
import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginResponse;
import com.project.finanacedashboardbackend.rest.dto.UserManagement.SignUpRequest;
import com.project.finanacedashboardbackend.rest.service.UserManagement.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SignUpControllerImpl implements SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpControllerImpl(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @Override
    public ResponseEntity<?> addUser(SignUpRequest signUpRequest) {

        try {
            LoginResponse loginResponse = signUpService.addUser(signUpRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (EmailAlreadyExistsException e) {
            // email already exists
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        } catch (DatabaseException e) {
            //generic database errors
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            //other unexpected exceptions
            ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
