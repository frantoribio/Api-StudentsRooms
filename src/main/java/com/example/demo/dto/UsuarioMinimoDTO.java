package com.example.demo.dto;

import java.util.UUID;

/**
 * DTO que representa la información mínima de un usuario.
 */
public class UsuarioMinimoDTO {
    
    private UUID id;
    private String nombre;
    private String email;
    private String rol; // Opcional, pero bueno para contexto

    // Constructor (para facilitar la conversión)
    public UsuarioMinimoDTO(UUID id, String nombre, String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    // Constructor vacío por defecto
    public UsuarioMinimoDTO() {}

    // Getters y Setters
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}