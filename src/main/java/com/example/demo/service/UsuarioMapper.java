package com.example.demo.service;
import com.example.demo.dto.HabitacionMinimaDTO;
import com.example.demo.dto.UsuarioDTO; 
import com.example.demo.model.Habitacion;
import com.example.demo.model.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades y DTOs de Usuario y Habitacion.
 */
@Component
public class UsuarioMapper {

    /**
     * Convierte una entidad Usuario a un DTO UsuarioDTO.
     * 
     * @param usuario Entidad Usuario.
     * @return DTO UsuarioDTO.
     */
    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());       
        return dto;
    }
    
    /**
     * Convierte una entidad Habitacion a un DTO HabitacionMinimaDTO.
     * 
     * @param habitacion Entidad Habitacion.
     * @return DTO HabitacionMinimaDTO.
     */
    public HabitacionMinimaDTO toHabitacionMinimaDTO(Habitacion habitacion) {
        HabitacionMinimaDTO dto = new HabitacionMinimaDTO();
        dto.setId(habitacion.getId());
        dto.setTitulo(habitacion.getTitulo());
        dto.setCiudad(habitacion.getCiudad());
        dto.setDireccion(habitacion.getDireccion());
        dto.setPrecioMensual(habitacion.getPrecioMensual());
        return dto;
    }

    /**
     * Convierte una lista de entidades Usuario a una lista de DTOs UsuarioDTO.
     * 
     * @param usuarios Lista de entidades Usuario.
     * @return Lista de DTOs UsuarioDTO.
     */
    public List<UsuarioDTO> toDTO(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Convierte un DTO UsuarioDTO a una entidad Usuario.
     * 
     * @param dto DTO UsuarioDTO.
     * @return Entidad Usuario.
     */
    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        return usuario;
    }   
}