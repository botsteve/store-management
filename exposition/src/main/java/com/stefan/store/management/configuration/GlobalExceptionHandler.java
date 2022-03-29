package com.stefan.store.management.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    protected Exception handleError(Exception ex) {
        return new Exception(ex.getLocalizedMessage());
    }
}
