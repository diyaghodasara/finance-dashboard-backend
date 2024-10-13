package com.project.finanacedashboardbackend.rest.security;

import com.project.finanacedashboardbackend.rest.entity.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SimpleJdbcCall jdbcCall;

    @Autowired
    public CustomUserDetailsService(@Qualifier("LoginUser") SimpleJdbcCall jdbcCall) {
        this.jdbcCall = jdbcCall;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Configure the stored procedure with the relevant parameters
        jdbcCall.withProcedureName("LoginUser")
                .declareParameters(
                        new SqlParameter("email", Types.VARCHAR),
                        new SqlOutParameter("out_name", Types.VARCHAR),
                        new SqlOutParameter("out_user_id", Types.INTEGER),
                        new SqlOutParameter("out_hashed_password", Types.VARCHAR),
                        new SqlOutParameter("out_message", Types.VARCHAR)
                );

        // Execute the stored procedure with the provided email
        Map<String, Object> result = jdbcCall.execute(Map.of("email", email));

        // Handle the response
        String outMessage = (String) result.get("out_message");
        if (result == null || result.isEmpty() || !"Success: User details retrieved.".equals(outMessage)) {
            throw new UsernameNotFoundException("User not found with Email: " + email);
        }

        // Extract user information from the result
        String hashedPassword = (String) result.get("out_hashed_password");
        Integer userId = (Integer) result.get("out_user_id");
        String firstName = (String) result.get("out_name");

        // Create and return a CustomUserDetails instance
        return new CustomUserDetails(email, hashedPassword, userId, firstName);
    }
}
