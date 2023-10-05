package com.userservice.exception;

import com.userservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>hendlerResourceNotFoundException(ResourceNotFoundException ex){
       String message= ex.getMessage();
        ApiResponse response=ApiResponse.builder().messgage(message).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);


    }
}
