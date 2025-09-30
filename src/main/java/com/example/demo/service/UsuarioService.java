package com.example.demo.service;

import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void eliminarPorId(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario guardar(Usuario usuario) {
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(contraseñaEncriptada);
        return usuarioRepository.save(usuario);
    }

     public Optional<Usuario> findById(UUID id) {
        return usuarioRepository.findById(id);
    }  
}
