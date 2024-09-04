package com.project.finanacedashboardbackend.rest.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final SimpleJdbcCall jdbcCall;

    @Autowired
    public CustomUserDetailsService(@Qualifier("registerUser") SimpleJdbcCall jdbcCall) {
        this.jdbcCall = jdbcCall;
    }

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        jdbcCall.withProcedureName("getUserDetails")
                .declareParameters(
                        new SqlParameter("p_Email", Types.VARCHAR),
                        new SqlOutParameter("o_Email",Types.VARCHAR),
                        new SqlOutParameter("o_Password",Types.VARCHAR)
                );
        Map<String, Object> result = jdbcCall.execute(Map.of("p_Email", Email));

        if (result == null || result.isEmpty()) {
            throw new UsernameNotFoundException("User not found with Email: " + Email);
        }

        String password = (String) result.get("password");

        return org.springframework.security.core.userdetails.User
                .withUsername(Email)
                .password(password)
                .authorities("USER")
                .build();
    }
}
