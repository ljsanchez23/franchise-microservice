package com.franchise_microservice.configuration;

import com.franchise_microservice.configuration.util.ConfigurationConstants;
import com.franchise_microservice.configuration.util.ErrorResponse;
import com.franchise_microservice.domain.exception.BranchAlreadyExistsException;
import com.franchise_microservice.domain.exception.BranchNotFoundException;
import com.franchise_microservice.domain.exception.FranchiseAlreadyExistsException;
import com.franchise_microservice.domain.exception.FranchiseNotFoundException;
import com.franchise_microservice.domain.exception.ProductAlreadyExistsException;
import com.franchise_microservice.domain.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBranchAlreadyExistsException(BranchAlreadyExistsException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBranchNotFoundException(BranchNotFoundException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleFranchiseAlreadyExistsException(FranchiseAlreadyExistsException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleFranchiseNotFoundException(FranchiseNotFoundException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleProductAlreadyExistsException(ProductAlreadyExistsException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    private HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (attributes != null) ? attributes.getRequest() : null;
    }
}
