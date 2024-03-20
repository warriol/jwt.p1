package com.example.demo.service.Impl;

import com.example.demo.constantes.UsuarioConstantes;
import com.example.demo.pojo.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioServicio;
import com.example.demo.util.UsuarioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServicioImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ResponseEntity<String> registrarUsuario(Map<String, String> requestMap) {
        log.info("Registro de Usuario {}", requestMap);
        try {
            if (validarRegistroUsuario(requestMap)) {
                Usuario usuario = usuarioRepository.findByEmail(requestMap.get("email"));
                if (Objects.isNull(usuario)) {
                    usuarioRepository.save(getUsuarioDesdeMap(requestMap));
                    return UsuarioUtils.getResponseEntity(UsuarioConstantes.USUARIO_REGISTRADO, HttpStatus.OK);
                }
                return UsuarioUtils.getResponseEntity(UsuarioConstantes.USUARIO_YA_EXISTE, HttpStatus.BAD_REQUEST);
            }
            return UsuarioUtils.getResponseEntity(UsuarioConstantes.USUARIO_NO_REGISTRADO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error al registrar usuario {}", e.getMessage());
            return UsuarioUtils.getResponseEntity(UsuarioConstantes.ALGO_SALIO_MAL, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validarRegistroUsuario (Map<String, String> requestMap) {
        if (requestMap.containsKey("nombre") &&
                requestMap.containsKey("apellido") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("password") &&
                requestMap.containsKey("telefono")) {
            return true;
        }
        return false;
    }
    private Usuario getUsuarioDesdeMap (Map<String, String> requestMap) {
        Usuario usuario = new Usuario();
        usuario.setNombre(requestMap.get("nombre"));
        usuario.setApellido(requestMap.get("apellido"));
        usuario.setEmail(requestMap.get("email"));
        usuario.setPassword(requestMap.get("password"));
        usuario.setTelefono(requestMap.get("telefono"));
        usuario.setStatus(Boolean.valueOf("false"));
        usuario.setRol("usuario");
        return usuario;
    }
}
