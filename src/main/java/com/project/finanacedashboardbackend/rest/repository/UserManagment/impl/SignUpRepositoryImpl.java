package com.project.finanacedashboardbackend.rest.repository.UserManagment.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailAlreadyExistsException;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.SignUpRequestEntity;
import com.project.finanacedashboardbackend.rest.repository.UserManagment.SignUpRespository;
import com.project.finanacedashboardbackend.rest.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Map;

@Repository
public class SignUpRepositoryImpl implements SignUpRespository {

    private final SimpleJdbcCall simpleJdbcCall;
    private final JwtUtil jwtUtil;


    @Autowired
    public SignUpRepositoryImpl(
            @Qualifier("SignUpUser") SimpleJdbcCall simpleJdbcCall,
            JwtUtil jwtUtil) {
        this.simpleJdbcCall = simpleJdbcCall;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseEntity addUser(SignUpRequestEntity signUpRequestEntity) {
        try {

            // Prepare the parameters
            simpleJdbcCall.withProcedureName("SignUpUser").withSchemaName("FinanceDashboard")
                    .declareParameters(
                            new SqlParameter("in_email", Types.VARCHAR),
                            new SqlParameter("in_password_hash", Types.VARCHAR),
                            new SqlParameter("in_name", Types.VARCHAR),
                            new SqlOutParameter("out_user_id", Types.INTEGER),
                            new SqlOutParameter("out_message", Types.VARCHAR)
                    );

            Map<String, Object> params = Map.of(
                    "in_email", signUpRequestEntity.getEmail(),
                    "in_password_hash", signUpRequestEntity.getHashedPassword(),
                    "in_name", signUpRequestEntity.getName()
            );

            // Execute the stored procedure
            Map<String, Object> result = simpleJdbcCall.execute(params);

            // Check for error message
            String outMessage = (String) result.get("out_message");
            if (outMessage != null && !outMessage.isEmpty() && !"Success".equalsIgnoreCase(outMessage)) {
                if (outMessage.contains("Email already exists")) {
                    throw new EmailAlreadyExistsException(outMessage);
                } else {
                    throw new DatabaseException(outMessage);
                }
            }

            // Extract UserID from the result
            Integer userId = (Integer) result.get("out_user_id");

            // Generate token
            String token = jwtUtil.generateToken(signUpRequestEntity.getEmail());

            // Create LoginResponseEntity
            LoginResponseEntity loginResponseEntity = new LoginResponseEntity();
            loginResponseEntity.setUserId(userId);
            loginResponseEntity.setName(signUpRequestEntity.getName());
            loginResponseEntity.setToken(token);

            // Return response entity
            return loginResponseEntity;

        } catch (EmailAlreadyExistsException | DatabaseException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
    }
}
