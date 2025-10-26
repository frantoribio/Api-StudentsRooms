package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * DTO que representa la información de una habitación.
 */
public class HabitacionDTO {
    
    private UUID id;
    private String titulo;
    private String ciudad;
    private String direccion;
    private BigDecimal precioMensual;
    private String descripcion;
    private List<String> imagenesUrl;
    
    // Aquí usamos el DTO Mínimo para el propietario, ¡rompiendo el ciclo!
    private UsuarioMinimoDTO propietario; 
    
    // Opcionalmente, puedes ignorar 'reservas' o crear un 'ReservaMinimaDTO'
    // private List<ReservaMinimaDTO> reservas; 

    // Constructor vacío por defecto
    public HabitacionDTO() {}
    
    // Getters y Setters

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

    public UsuarioMinimoDTO getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioMinimoDTO propietario) {
        this.propietario = propietario;
    }
}