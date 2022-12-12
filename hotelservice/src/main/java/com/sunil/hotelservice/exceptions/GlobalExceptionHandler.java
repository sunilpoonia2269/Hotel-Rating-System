package com.sunil.hotelservice.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sunil.hotelservice.config.AppConstants;
import com.sunil.hotelservice.payloads.ErrorResponse;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder().success(false).status(HttpStatus.NOT_FOUND)
                .message(exception.getLocalizedMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder().success(false).status(HttpStatus.BAD_REQUEST)
                .message(AppConstants.CONSTRAINT_VIOLATION_MESSAGE).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
