package com.example.demo.dto;

import java.time.LocalDate;
import java.util.UUID;
import com.example.demo.model.EstadoReserva;

import com.example.demo.model.Reserva;


/**
 * DTO para exponer reservas sin cargar entidades completas.
 */
public class ReservaDTO {
    private UUID id;
    private UUID habitacionId;
    private UUID alumnoId;
    private String alumnoEmail;      // 👈 nuevo campo
    private String propietarioEmail; // 👈 nuevo campo
    private UUID propietarioId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estadoReserva;

    public ReservaDTO() {}

    public ReservaDTO(UUID id, UUID habitacionId, UUID alumnoId, UUID propietarioId,
                      String alumnoEmail, String propietarioEmail,
                      LocalDate fechaInicio, LocalDate fechaFin, EstadoReserva estadoReserva) {
        this.id = id;
        this.habitacionId = habitacionId;
        this.alumnoId = alumnoId;
        this.propietarioId = propietarioId;
        this.alumnoEmail = alumnoEmail;
        this.propietarioEmail = propietarioEmail;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoReserva = estadoReserva;
    }

    
    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getHabitacionId() { return habitacionId; }
    public void setHabitacionId(UUID habitacionId) { this.habitacionId = habitacionId; }

    public UUID getAlumnoId() { return alumnoId; }
    public void setAlumnoId(UUID alumnoId) { this.alumnoId = alumnoId; }

    public UUID getPropietarioId() { return propietarioId; }
    public void setPropietarioId(UUID propietarioId) { this.propietarioId = propietarioId; }

    public String getAlumnoEmail() { return alumnoEmail; }
    public void setAlumnoEmail(String alumnoEmail) { this.alumnoEmail = alumnoEmail; }

    public String getPropietarioEmail() { return propietarioEmail; }
    public void setPropietarioEmail(String propietarioEmail) { this.propietarioEmail = propietarioEmail; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public EstadoReserva getEstadoReserva() { return estadoReserva; }
    public void setEstadoReserva(EstadoReserva estadoReserva) { this.estadoReserva = estadoReserva; }

    public ReservaDTO convertirAReservaDTO(Reserva reserva) {
        return new ReservaDTO(
            reserva.getId(),
            reserva.getHabitacion().getId(),
            reserva.getAlumno() != null ? reserva.getAlumno().getId() : null,
            reserva.getPropietario() != null ? reserva.getPropietario().getId() : null,
            reserva.getAlumno() != null ? reserva.getAlumno().getEmail() : null,
            reserva.getPropietario() != null ? reserva.getPropietario().getEmail() : null,
            reserva.getFechaInicio(),
            reserva.getFechaFin(),
            reserva.getEstadoReserva()
        );
    }

  
}
