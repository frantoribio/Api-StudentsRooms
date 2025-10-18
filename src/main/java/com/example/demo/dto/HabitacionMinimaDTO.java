package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO para la respuesta de una Habitacion cuando está anidada en otro objeto (ej. Usuario).
 * Excluye la referencia al propietario.
 */
public class HabitacionMinimaDTO {

    private UUID id;
    private String titulo;
    private String ciudad;
    private String direccion;
    private BigDecimal precioMensual;

    public HabitacionMinimaDTO() {}

    public HabitacionMinimaDTO(UUID id, String titulo, String ciudad, String direccion, BigDecimal precioMensual) {
        this.id = id;
        this.titulo = titulo;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.precioMensual = precioMensual;
    }

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
    
}