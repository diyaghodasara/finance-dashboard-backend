package com.project.finanacedashboardbackend.rest.config;

import com.project.finanacedashboardbackend.rest.constants.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

    @Bean(name = "getUserDetails")
    public SimpleJdbcCall getUserDetailsCall(DataSource dataSource) {
        return new SimpleJdbcCall(dataSource);
    }

    @Bean(name = "registerUser")
    public SimpleJdbcCall registerUserCall(DataSource dataSource) {
        return new SimpleJdbcCall(dataSource);
    }
}
