package com.franchise_microservice.domain.exception;

public class FranchiseAlreadyExistsException extends RuntimeException {
    public FranchiseAlreadyExistsException(String message) {
        super(message);
    }
}
