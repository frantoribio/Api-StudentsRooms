package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CrearHabitacionDto {
    private String titulo;
    private String ciudad;
    private String direccion;
    private BigDecimal precioMensual;
    private String descripcion;
    private List<String> imagenesUrl;
    private UUID propietarioId;

    // Getters y setters
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
    public UUID getPropietarioId() {
        return propietarioId;
    }
    public void setPropietarioId(UUID propietarioId) {
        this.propietarioId = propietarioId;
    }
}
