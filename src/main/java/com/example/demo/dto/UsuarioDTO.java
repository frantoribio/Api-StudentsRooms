package com.example.demo.dto;

import com.example.demo.model.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

/**
 * DTO para la respuesta de la API al listar/obtener un Usuario.
 * Excluye: listas de referencias (habitaciones/reservas) y detalles de UserDetails.
 */
public class UsuarioDTO {
    
    private UUID id;
    private String nombre;
    private String email;
    @JsonIgnore
    private String contraseña;
    private Rol rol; 

    // Opcional: Contadores para dar contexto sin causar recursión
    private int totalHabitacionesPublicadas;
    private int totalReservasRealizadas;

    public UsuarioDTO() {}

    public UsuarioDTO(UUID id, String nombre, String email, String contraseña, Rol rol, int totalHabitacionesPublicadas, int totalReservasRealizadas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.rol = rol;
        this.totalHabitacionesPublicadas = totalHabitacionesPublicadas;
        this.totalReservasRealizadas = totalReservasRealizadas;
    }

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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getTotalHabitacionesPublicadas() {
        return totalHabitacionesPublicadas;
    }

    public void setTotalHabitacionesPublicadas(int totalHabitacionesPublicadas) {
        this.totalHabitacionesPublicadas = totalHabitacionesPublicadas;
    }

    public int getTotalReservasRealizadas() {
        return totalReservasRealizadas;
    }

    public void setTotalReservasRealizadas(int totalReservasRealizadas) {
        this.totalReservasRealizadas = totalReservasRealizadas;
    }


    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
}
