package com.example.demo.service;

import com.example.demo.dto.CrearHabitacionDto;
import com.example.demo.dto.HabitacionDTO; 
import com.example.demo.model.Habitacion;
import com.example.demo.model.Usuario;
import com.example.demo.repository.HabitacionRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service; 
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para gestionar operaciones relacionadas con Habitacion.
 * Proporciona métodos para CRUD y utiliza HabitacionMapper para conversiones DTO.
 */
@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;
    
    // Inyectamos el nuevo Mapper
    @Autowired
    private HabitacionMapper habitacionMapper; 

    @Autowired
    private UsuarioRepository usuarioRepository;

    public HabitacionService(HabitacionRepository habitacionRepository, UsuarioRepository usuarioRepository) {
        this.habitacionRepository = habitacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtiene todas las habitaciones de la base de datos para el usuario autenticado.
     */
    public List<Habitacion> obtenerHabitacionesDelPropietarioAutenticado() {
    
        String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

        UUID propietarioId = usuarioRepository.findByEmail(emailAutenticado)
                                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"))
                                .getId();
        return habitacionRepository.findByPropietarioId(propietarioId);
    }

    /**
     * Método para obtener todas las habitaciones como DTOs.
     * @return Lista de HabitacionDTO
     */
    public List<HabitacionDTO> findAllDTO() {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        return habitacionMapper.toDTO(habitaciones); 
    }

    /**
     * Método para obtener una habitación por ID como DTO.
     * @param id
     * @return Optional de HabitacionDTO
     */
    public Optional<HabitacionDTO> findByIdDTO(UUID id) {
        return habitacionRepository.findById(id)
                                   .map(habitacionMapper::toDTO); // Mapeamos si está presente
    }

    /**
     * Método para obtener una habitación por ID.
     * @param id
     * @return Optional de Habitacion
     */
    public Optional<Habitacion> findById(UUID id) {
        return habitacionRepository.findById(id);
    }

    /**
     * Método para guardar o actualizar una habitación.
     * @param habitacion
     * @return Habitacion guardada
     */
    public Habitacion save(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }
    
    /**
     * Método para eliminar una habitación por ID.
     * @param id
     */
    public void deleteById(UUID id) {
        habitacionRepository.deleteById(id);
    }

    public Habitacion crearHabitacion(CrearHabitacionDto dto) {
        Habitacion habitacion = new Habitacion();
        habitacion.setId(UUID.randomUUID());
        habitacion.setTitulo(dto.getTitulo());
        habitacion.setCiudad(dto.getCiudad());
        habitacion.setDireccion(dto.getDireccion());
        habitacion.setPrecioMensual(dto.getPrecioMensual());
        habitacion.setDescripcion(dto.getDescripcion());

        // Buscar el propietario
        Usuario propietario = usuarioRepository.findById(dto.getPropietarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        habitacion.setPropietario(propietario);
        return habitacionRepository.save(habitacion);
    }

    public Usuario getUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    public Habitacion actualizaHabitacion(UUID id, Habitacion habitacionDetails) {
        Habitacion habitacion = habitacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Habitación no encontrada con id: " + id));

        habitacion.setTitulo(habitacionDetails.getTitulo());
        habitacion.setCiudad(habitacionDetails.getCiudad());
        habitacion.setDireccion(habitacionDetails.getDireccion());
        habitacion.setPrecioMensual(habitacionDetails.getPrecioMensual());
        habitacion.setDescripcion(habitacionDetails.getDescripcion());
        habitacion.setImagenesUrl(habitacionDetails.getImagenesUrl());

        return habitacionRepository.save(habitacion);
    }
    
}
