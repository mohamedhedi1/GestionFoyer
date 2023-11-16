package com.example.gestionfoyer.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionHandler extends Throwable  {
    public GlobalExceptionHandler(String errorMessage) {
        super(errorMessage);
    }
}
