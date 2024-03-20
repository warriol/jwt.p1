package com.example.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UsuarioUtils {

    private UsuarioUtils() {
        // throw new IllegalStateException("Utility class");
    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<String>("Mensaje: " + message, status);
    }
}
