package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID; 
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Usuario;

/**
 * Repositorio para la entidad Usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);    
} 
