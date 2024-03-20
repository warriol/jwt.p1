package com.example.demo.repository;

import com.example.demo.pojo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // metodo personalizado para buscar por email
    Usuario findByEmail(@Param(("email")) String email);
}
