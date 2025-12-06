package com.example.demo.model;

import jakarta.persistence.*;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Clase que representa un usuario en el sistema.
 */
@Entity
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;
    private String email;
    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Habitacion> habitacionesPublicadas = new ArrayList<>();

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.REMOVE)
    @JsonManagedReference("alumno-reservas")
    @JsonIgnore
    private List<Reserva> reservasRealizadas = new ArrayList<>();

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.REMOVE)
    @JsonManagedReference("propietario-reservas")
    @JsonIgnore
    private List<Reserva> reservasComoPropietario = new ArrayList<>();



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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.rol == null) {
            return List.of(); // devuelve vacío en vez de null
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getPassword() {
        return contraseña;
    }

    @Override
    public String getUsername() {
        return email;   
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }                       
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
