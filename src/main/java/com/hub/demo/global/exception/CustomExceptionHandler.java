package com.hub.demo.global.exception;

import com.hub.demo.global.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> customHandle(CustomException e){
        return new ResponseEntity<>(
                new Response(e.getStatus(), e.getMessage()),
                e.getStatus()
        );

    }
}