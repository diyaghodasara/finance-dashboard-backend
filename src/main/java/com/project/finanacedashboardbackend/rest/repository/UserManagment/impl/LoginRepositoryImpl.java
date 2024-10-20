package com.project.finanacedashboardbackend.rest.repository.UserManagment.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.EmailNotFoundException;
import com.project.finanacedashboardbackend.rest.config.Exception.IncorrectPasswordException;
import com.project.finanacedashboardbackend.rest.security.CustomUserDetails;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginRequestEntity;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.repository.UserManagment.LoginRepository;
import com.project.finanacedashboardbackend.rest.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginRepositoryImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseEntity login(LoginRequestEntity loginRequestEntity) {
        try {
            // Authenticate using Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestEntity.getEmail(),
                            loginRequestEntity.getPassword()
                    )
            );

            // Generate JWT if authentication is successful
            String token = jwtUtil.generateToken(loginRequestEntity.getEmail());

            // Extract user details from the authentication object

            Integer userId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();
            String firstName = ((CustomUserDetails) authentication.getPrincipal()).getFirstName();

            // Create and return the response entity
            LoginResponseEntity loginResponseEntity = new LoginResponseEntity();
            loginResponseEntity.setToken(token);
            loginResponseEntity.setUserId(userId);
            loginResponseEntity.setName(firstName);
            return loginResponseEntity;

        } catch (UsernameNotFoundException e) {
            throw new EmailNotFoundException("Email not found.");
        } catch (BadCredentialsException e) {
            throw new IncorrectPasswordException("Password mismatch.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during login: " + e.getMessage());
        }
    }
}
