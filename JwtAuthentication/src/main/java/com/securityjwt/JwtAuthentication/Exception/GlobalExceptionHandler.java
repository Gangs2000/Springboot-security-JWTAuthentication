package com.securityjwt.JwtAuthentication.Exception;

import com.securityjwt.JwtAuthentication.Document.ExceptionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired ExceptionTemplate exceptionTemplate;

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity<ExceptionTemplate> exceptionHandler(GlobalException globalException){
        exceptionTemplate.setErrorStatus(String.valueOf(HttpStatus.UNAUTHORIZED));
        exceptionTemplate.setErrorMessage(globalException.getMessage());
        exceptionTemplate.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionTemplate, HttpStatus.UNAUTHORIZED);
    }
}
