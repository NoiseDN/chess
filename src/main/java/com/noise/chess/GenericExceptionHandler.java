package com.noise.chess;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GenericExceptionHandler {
    @ExceptionHandler
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(500).build();
    }
}
