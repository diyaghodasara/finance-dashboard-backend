package com.project.finanacedashboardbackend.rest.controller.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailAlreadyExistsException;
import com.project.finanacedashboardbackend.rest.controller.SignUpController;
import com.project.finanacedashboardbackend.rest.dto.ErrorResponse;
import com.project.finanacedashboardbackend.rest.dto.LoginResponse;
import com.project.finanacedashboardbackend.rest.dto.SignUpRequest;
import com.project.finanacedashboardbackend.rest.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SignUpControllerImpl implements SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpControllerImpl(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @Override
    public ResponseEntity<?> addUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            //add a new user
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
