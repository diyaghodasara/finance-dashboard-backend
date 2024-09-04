package com.project.finanacedashboardbackend.rest.repository.impl;

import com.project.finanacedashboardbackend.rest.config.Exception.DatabaseException;
import com.project.finanacedashboardbackend.rest.config.Exception.EmailNotFoundException;
import com.project.finanacedashboardbackend.rest.config.Exception.IncorrectPasswordException;
import com.project.finanacedashboardbackend.rest.entity.LoginRequestEntity;
import com.project.finanacedashboardbackend.rest.entity.LoginResponseEntity;
import com.project.finanacedashboardbackend.rest.repository.LoginRepository;
import com.project.finanacedashboardbackend.rest.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginRepositoryImpl implements LoginRepository {
    private final SimpleJdbcCall simpleJdbcCall;
    private final JwtUtil jwtUtil;
    @Autowired
    public LoginRepositoryImpl(@Qualifier("LoginUser") SimpleJdbcCall simpleJdbcCall, JwtUtil jwtUtil) {
        this.simpleJdbcCall = simpleJdbcCall;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public LoginResponseEntity login(LoginRequestEntity loginRequestEntity) {
        try {
            simpleJdbcCall.withSchemaName("FinanceDashboard").withProcedureName("LoginUser")
                    .declareParameters(
                            new SqlParameter("p_Email", Types.VARCHAR),
                            new SqlParameter("p_Password", Types.VARCHAR),
                            new SqlOutParameter("o_UserID", Types.INTEGER),
                            new SqlOutParameter("o_FirstName", Types.VARCHAR),
                            new SqlOutParameter("o_ErrorMsg", Types.VARCHAR)
                    );
            Map<String,Object> params = new HashMap<>();
            params.put("p_Email", loginRequestEntity.getEmail());
            params.put("p_Password", loginRequestEntity.getPassword());
            Map<String, Object> result = simpleJdbcCall.execute(params);
            LoginResponseEntity loginResponseEntity = new LoginResponseEntity();
            String ErrorMsg = (String) result.get("o_ErrorMsg");
            if(ErrorMsg != null || !ErrorMsg.isEmpty()) {
                if(ErrorMsg.equals("Email not found.")){
                    throw new EmailNotFoundException(ErrorMsg);
                } else if(ErrorMsg.equals("Password mismatch.")){
                    throw new IncorrectPasswordException(ErrorMsg);
                }
            }
            //Generate token
            String token = jwtUtil.generateToken(loginRequestEntity.getEmail());
            loginResponseEntity.setToken(token);
            loginResponseEntity.setUserId((Integer)  result.get("o_UserID"));
            loginResponseEntity.setFirstName((String) result.get("o_FirstName"));
            return loginResponseEntity;
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
