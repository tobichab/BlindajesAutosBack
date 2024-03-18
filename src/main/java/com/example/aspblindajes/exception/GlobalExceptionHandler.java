package com.example.aspblindajes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> ResourceNotFoundHandler (ResourceNotFoundException resourceNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> ResourceAlreadyExistsHandler(ResourceAlreadyExistsException resourceAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<String> InvalidArgumentHandler(InvalidArgumentException invalidArgumentException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidArgumentException.getMessage());
    }

}
