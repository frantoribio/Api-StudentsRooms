package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.Usuario;
import com.example.demo.security.JwtUtil;

/**
 * Controlador REST para la autenticación de usuarios.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    /**
     * Constructor para inyectar dependencias.
     * 
     * @param authManager Administrador de autenticación
     * @param jwtUtil Utilidad para manejar JWT
     */
    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Maneja la solicitud de inicio de sesión.
     * 
     * @param request Datos de autenticación
     * @return Respuesta con token JWT o error de autenticación
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody AuthRequest request) {
        try {
            Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getContraseña())
            );

            Usuario usuario = (Usuario) auth.getPrincipal();
            String token = jwtUtil.generarToken(auth);
            String rol = usuario.getRol() != null ? usuario.getRol().name() : null;
            
            AuthResponse response = new AuthResponse(request.getEmail(), token, rol);
            
            return ResponseEntity.ok(response);

            
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }  
}
