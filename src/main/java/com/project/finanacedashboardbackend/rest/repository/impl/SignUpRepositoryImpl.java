package com.project.finanacedashboardbackend.rest.repository.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailAlreadyExistsException;
import com.project.finanacedashboardbackend.rest.entity.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.entity.SignUpRequestEntity;
import com.project.finanacedashboardbackend.rest.repository.SignUpRespository;
import com.project.finanacedashboardbackend.rest.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.sql.Types;
import java.util.Map;

@Repository
public class SignUpRepositoryImpl implements SignUpRespository {

    private final SimpleJdbcCall simpleJdbcCall;
    private final JwtUtil jwtUtil;
    @Autowired
    public SignUpRepositoryImpl(@Qualifier("getUserDetails") SimpleJdbcCall simpleJdbcCall, JwtUtil jwtUtil) {
        this.simpleJdbcCall = simpleJdbcCall;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseEntity addUser(SignUpRequestEntity signUpRequestEntity) {
        try {
            // Prepare the parameters
            simpleJdbcCall.withProcedureName("RegisterUser").withSchemaName("FinanceDashboard")
                    .declareParameters(
                            new SqlParameter("p_FirstName", Types.VARCHAR),
                            new SqlParameter("p_LastName", Types.VARCHAR),
                            new SqlParameter("p_Email", Types.VARCHAR),
                            new SqlParameter("p_Password", Types.VARCHAR),
                            new SqlOutParameter("p_UserID", Types.INTEGER),
                            new SqlOutParameter("p_ErrorMsg", Types.VARCHAR)
                    );
            Map<String, Object> params = Map.of(
                    "p_FirstName", signUpRequestEntity.getFirstName(),
                    "p_LastName", signUpRequestEntity.getLastName(),
                    "p_Email", signUpRequestEntity.getEmail(),
                    "p_Password", signUpRequestEntity.getPassword()
            );

            // Execute the stored procedure
            Map<String, Object> result = simpleJdbcCall.execute(params);

            // Check for error message
            String errorMsg = (String) result.get("p_ErrorMsg");
            if (errorMsg != null && !errorMsg.isEmpty()) {
                if ("Email already exists.".equals(errorMsg)) {
                    throw new EmailAlreadyExistsException(errorMsg);
                } else if ("An error occurred during registration.".equals(errorMsg)) {
                    throw new DatabaseException(errorMsg);
                }

            }

            // Extract UserID from the result
            Integer userId = (Integer) result.get("p_UserID");

            //Generate token
            String token = jwtUtil.generateToken(signUpRequestEntity.getEmail());

            //LoginResponse Entity
            LoginResponseEntity loginResponseEntity = new LoginResponseEntity();
            loginResponseEntity.setUserId(userId);
            loginResponseEntity.setFirstName(signUpRequestEntity.getFirstName());
            loginResponseEntity.setToken(token);
            // Return response entity
            return loginResponseEntity;

        } catch (EmailAlreadyExistsException e) {
            throw new EmailAlreadyExistsException(e.getMessage());
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
