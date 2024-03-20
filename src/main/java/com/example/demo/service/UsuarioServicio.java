package com.example.demo.service;

import com.example.demo.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UsuarioServicio {

    ResponseEntity<String> registrarUsuario(Map<String,String> requestMap);
}
