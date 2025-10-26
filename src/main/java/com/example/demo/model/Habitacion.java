package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

/**
 * Clase que representa una habitación disponible para alquiler.
 */
@Entity
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //@NotBlank(message = "No puede estar vacío")
    private String titulo;
    
    //@NotBlank(message = "No puede estar vacío")
    private String ciudad;

    //@NotBlank(message = "No puede estar vacío")
    private String direccion;

    //@NotNull(message = "El precio no puede ser nulo")
    //@Min(value = 0, message = "El precio no puede ser negativo")
    private BigDecimal precioMensual;
    
    //@NotBlank(message = "No puede estar vacío")
    private String descripcion;

    @ElementCollection
    private List<String> imagenesUrl = new ArrayList<>();

    @ManyToOne
    private Usuario propietario;

    @OneToMany(mappedBy = "habitacion")
    @JsonBackReference
    private List<Reserva> reservas = new ArrayList<>();

    // Getters y setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getPrecioMensual() {
        return precioMensual;
    }

    public void setPrecioMensual(BigDecimal precioMensual) {
        this.precioMensual = precioMensual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getImagenesUrl() {
        return imagenesUrl;
    }

    public void setImagenesUrl(List<String> imagenesUrl) {
        this.imagenesUrl = imagenesUrl;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
}
