package com.example.demo.service;
import com.example.demo.dto.HabitacionMinimaDTO;
import com.example.demo.dto.UsuarioDTO; 
import com.example.demo.model.Habitacion;
import com.example.demo.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

// ... imports

@Component
public class UsuarioMapper {

    // Método principal para listar todos los usuarios
    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        
        // 1. Mapeo de campos seguros
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        
    

        // Si realmente necesitas devolver la lista de habitaciones:
        // dto.setHabitacionesPublicadas(usuario.getHabitacionesPublicadas().stream()
        //      .map(this::toHabitacionMinimaDTO)
        //      .collect(Collectors.toList()));
        
        return dto;
    }
    
    // Método para crear la HabitacionMinimaDTO (si se necesita)
    public HabitacionMinimaDTO toHabitacionMinimaDTO(Habitacion habitacion) {
        HabitacionMinimaDTO dto = new HabitacionMinimaDTO();
        dto.setId(habitacion.getId());
        dto.setTitulo(habitacion.getTitulo());
        dto.setCiudad(habitacion.getCiudad());
        dto.setDireccion(habitacion.getDireccion());
        // Excluir 'propietario' según el comentario
        return dto;
    }

    public List<UsuarioDTO> toDTO(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
}