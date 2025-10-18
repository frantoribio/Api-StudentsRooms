package com.example.demo.service; // o com.example.demo.service

import com.example.demo.dto.HabitacionDTO;
import com.example.demo.dto.UsuarioMinimoDTO;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // Marca esta clase como un Bean de Spring
public class HabitacionMapper {

    /**
     * Convierte una entidad Habitacion a su DTO correspondiente.
     */
    public HabitacionDTO toDTO(Habitacion habitacion) {
        if (habitacion == null) {
            return null;
        }

        HabitacionDTO dto = new HabitacionDTO();
        
        // Mapear campos simples de Habitacion
        dto.setId(habitacion.getId());
        dto.setTitulo(habitacion.getTitulo());
        dto.setCiudad(habitacion.getCiudad());
        dto.setDireccion(habitacion.getDireccion());
        dto.setPrecioMensual(habitacion.getPrecioMensual());
        dto.setDescripcion(habitacion.getDescripcion());
        dto.setImagenesUrl(habitacion.getImagenesUrl());

        // Mapear la entidad Usuario a UsuarioMinimoDTO para romper la recursión
        if (habitacion.getPropietario() != null) {
            dto.setPropietario(toUsuarioMinimoDTO(habitacion.getPropietario()));
        }

        return dto;
    }

    /**
     * Convierte una entidad Usuario a su DTO mínimo.
     */
    public UsuarioMinimoDTO toUsuarioMinimoDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        
        // Mapear solo los campos seguros y necesarios
        UsuarioMinimoDTO dto = new UsuarioMinimoDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol() != null ? usuario.getRol().name() : null); // Convertir Rol enum a String
        
        return dto;
    }

    /**
     * Convierte una lista de entidades Habitacion a una lista de DTOs.
     */
    public List<HabitacionDTO> toDTO(List<Habitacion> habitaciones) {
        return habitaciones.stream()
                           .map(this::toDTO)
                           .collect(Collectors.toList());
    }
    
}