package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class CrearHabitacionRequest {
    private String titulo;
    private String ciudad;
    private String direccion;
    private BigDecimal precioMensual;
    private String descripcion;
    private List<String> imagenesUrl;
    

    // Getters and Setters
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


}
