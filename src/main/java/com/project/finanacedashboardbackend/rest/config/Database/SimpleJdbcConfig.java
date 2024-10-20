package com.project.finanacedashboardbackend.rest.config.Database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;

@Configuration
public class SimpleJdbcConfig {

    @Bean(name = "SignUpUser")
    public SimpleJdbcCall registerUserCall(DataSource dataSource) {
        return new SimpleJdbcCall(dataSource);
    }

    @Bean(name = "LoginUser")
    public SimpleJdbcCall loginUserCall(DataSource dataSource) {
        return new SimpleJdbcCall(dataSource);
    }

    @Bean(name = "AddGoal")
    public SimpleJdbcCall addGoalCall(DataSource dataSource) {
        return new SimpleJdbcCall(dataSource);
    }

    @Bean(name = "UpdateGoal")
    public SimpleJdbcCall updateGoalCall(DataSource dataSource) {
        return new SimpleJdbcCall(dataSource);
    }

    @Bean(name = "DeleteGoal")
    public SimpleJdbcCall deleteGoalCall(DataSource dataSource) {
        return new SimpleJdbcCall(dataSource);
    }
}
