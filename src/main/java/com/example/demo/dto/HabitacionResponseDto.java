package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class HabitacionResponseDto {
    private UUID id;
    private String titulo;
    private String ciudad;
    private BigDecimal precioMensual;
    private String descripcion;
    private List<String> imagenesUrl;

    // Getters and Setters
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
    

}
