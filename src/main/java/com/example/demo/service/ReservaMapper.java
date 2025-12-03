package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.model.Reserva;

@Component
public class ReservaMapper {
   public ReservaDTO toDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setEstadoReserva(reserva.getEstadoReserva());

        if (reserva.getAlumno() != null) {
            dto.setAlumnoId(reserva.getAlumno().getId());
        }

        if (reserva.getPropietario() != null) {
            dto.setPropietarioId(reserva.getPropietario().getId());
        }

        if (reserva.getHabitacion() != null) {
            dto.setHabitacionId(reserva.getHabitacion().getId());
        }
 
        return dto;
    }

    public List<ReservaDTO> toDTOList(List<Reserva> reservas) {
        return reservas.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
