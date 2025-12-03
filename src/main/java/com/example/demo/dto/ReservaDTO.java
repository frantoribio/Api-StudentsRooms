package com.example.demo.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.model.EstadoReserva;
import com.example.demo.model.Reserva;

public class ReservaDTO {
    private UUID id;
    private UUID habitacionId;
    private UUID alumnoId;
    private UUID propietarioId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estadoReserva;

    public ReservaDTO(UUID id, UUID habitacionId, UUID alumnoId, UUID propietarioId, LocalDate fechaInicio, LocalDate fechaFin, EstadoReserva estadoReserva) {
        this.id = id;
        this.habitacionId = habitacionId;
        this.alumnoId = alumnoId;
        this.propietarioId = propietarioId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoReserva = estadoReserva;
    }
    public ReservaDTO() {
    }

    // Getters y Setters
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
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }
    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public ReservaDTO toDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setEstadoReserva(reserva.getEstadoReserva());
        dto.setAlumnoId(reserva.getAlumno().getId());
        dto.setPropietarioId(reserva.getPropietario().getId());
        dto.setHabitacionId(reserva.getHabitacion().getId());
        return dto;
    }

}
