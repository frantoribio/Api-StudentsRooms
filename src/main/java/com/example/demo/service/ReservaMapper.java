package com.example.demo.service;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.model.Reserva;

@Component
public class ReservaMapper {

    public static ReservaDTO toDTO(Reserva reserva) {
        if (reserva == null) return null;

        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setEstadoReserva(reserva.getEstadoReserva());
        dto.setAlumnoId(reserva.getAlumno() != null ? reserva.getAlumno().getId() : null);
        dto.setPropietarioId(reserva.getPropietario() != null ? reserva.getPropietario().getId() : null);
        dto.setHabitacionId(reserva.getHabitacion() != null ? reserva.getHabitacion().getId() : null);
        return dto;
    }
}