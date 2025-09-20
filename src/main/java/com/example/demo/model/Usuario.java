package com.example.demo.model;

import jakarta.persistence.*;
import java.util.*;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;
    private String email;
    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "propietario")
    private List<Habitacion> habitacionesPublicadas = new ArrayList<>();

    @OneToMany(mappedBy = "alumno")
    private List<Reserva> reservasRealizadas = new ArrayList<>();

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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Habitacion> getHabitacionesPublicadas() {
        return habitacionesPublicadas;
    }

    public void setHabitacionesPublicadas(List<Habitacion> habitacionesPublicadas) {
        this.habitacionesPublicadas = habitacionesPublicadas;
    }

    public List<Reserva> getReservasRealizadas() {
        return reservasRealizadas;
    }

    public void setReservasRealizadas(List<Reserva> reservasRealizadas) {
        this.reservasRealizadas = reservasRealizadas;
    }
    
}
