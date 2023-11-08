package com.vaibhav.security.controllers;

import com.vaibhav.security.exception.InvalidCredential;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(InvalidCredential.class)
    public ResponseEntity<String> invalidCred(InvalidCredential invalidCredential)
    {

        return new ResponseEntity<String>(invalidCredential.getMessage(), HttpStatus.OK);
    }
}
