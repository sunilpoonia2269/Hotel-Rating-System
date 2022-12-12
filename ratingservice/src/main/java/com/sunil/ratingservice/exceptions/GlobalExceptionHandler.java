package com.sunil.ratingservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.sunil.ratingservice.config.AppConstants;
import com.sunil.ratingservice.payloads.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(exception.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND).success(false).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> duplicateKeyExceptionHandler(DuplicateKeyException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(AppConstants.DUPLICATE_ERROR)
                .status(HttpStatus.BAD_REQUEST).success(false).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("success", "false");
        response.put("status", "400");
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String keyName = ((FieldError) error).getField();
            String value = error.getDefaultMessage();
            response.put(keyName, value);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
