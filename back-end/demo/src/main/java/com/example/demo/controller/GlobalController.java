package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;



@RestControllerAdvice //to make it global
public class GlobalController {
            @ExceptionHandler(IllegalArgumentException.class)
            @ResponseStatus(HttpStatus.BAD_REQUEST)
        public Map<String, Object> handleBadRequest(IllegalArgumentException e) {
            return Map.of(
                    "error", "BAD_REQUEST",
                    "message", e.getMessage(), //shows the exact error
                    "timestamp", LocalDateTime.now().toString()
            );
        }
            @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        return Map.of(
                "error", "BAD_REQUEST",
                "message", "Invalid input",
                "timestamp", LocalDateTime.now().toString()
        );
    }
}
