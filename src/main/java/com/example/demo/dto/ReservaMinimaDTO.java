package com.example.demo.dto;

import java.util.UUID;

public class ReservaMinimaDTO {
    private UUID id;
    private UUID habitacionId;
    private UUID alumnoId;
    private UUID propietarioId;
    private String estadoReserva;
    private String fechaInicio;
    private String fechaFin;

    public ReservaMinimaDTO() {}

    public ReservaMinimaDTO(UUID id, UUID habitacionId, UUID alumnoId, UUID propietarioId, String estadoReserva, String fechaInicio, String fechaFin) {
        this.id = id;
        this.habitacionId = habitacionId;
        this.alumnoId = alumnoId;
        this.propietarioId = propietarioId;
        this.estadoReserva = estadoReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getHabitacionId() {
        return habitacionId;
    }
    public void setHabitacionId(UUID habitacionId) {
        this.habitacionId = habitacionId;
    }
    public UUID getAlumnoId() {
        return alumnoId;
    }
    public void setAlumnoId(UUID alumnoId) {
        this.alumnoId = alumnoId;
    }
    public UUID getPropietarioId() {
        return propietarioId;
    }
    public void setPropietarioId(UUID propietarioId) {
        this.propietarioId = propietarioId;
    }
    public String getEstadoReserva() {
        return estadoReserva;
    }
    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}