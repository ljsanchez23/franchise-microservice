package com.franchise_microservice.adapters.aws.util.exception;

public class DatabaseCredentialsException extends RuntimeException {
    public DatabaseCredentialsException(String message) {
        super(message);
    }
}
