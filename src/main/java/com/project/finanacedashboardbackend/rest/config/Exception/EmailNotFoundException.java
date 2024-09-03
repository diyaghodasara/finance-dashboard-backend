package com.project.finanacedashboardbackend.rest.config.Exception;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String message) {
        super(message);
    }
}
