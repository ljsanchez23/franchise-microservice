package com.franchise_microservice.domain.exception;

public class FranchiseNotFoundException extends RuntimeException {
    public FranchiseNotFoundException(String message) {
        super(message);
    }
}
