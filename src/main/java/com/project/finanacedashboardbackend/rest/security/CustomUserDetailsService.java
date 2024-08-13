package com.project.finanacedashboardbackend.rest.security;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private SimpleJdbcCall jdbcCall;

    @Autowired
    public CustomUserDetailsService(SimpleJdbcCall jdbcCall) {
//        this.jdbcCall = new SimpleJdbcCall(dataSource)
//                .withProcedureName("getUserByUsername");
        this.jdbcCall = jdbcCall;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        jdbcCall.withProcedureName("getUserByUsername");
        Map<String, Object> result = jdbcCall.execute(Map.of("username", username));

        if (result == null || result.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        String password = (String) result.get("password");

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(password)
                .authorities("USER")
                .build();
    }
}
