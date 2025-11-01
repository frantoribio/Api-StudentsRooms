package com.example.demo.service;

import com.example.demo.dto.HabitacionDTO; 
import com.example.demo.model.Habitacion;
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
        // 1. Obtener el email (o el campo que uses como 'Principal') del usuario autenticado
        String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2. Buscar el ID del Usuario (Propietario)
        // Se asume que getUsuarioPorEmail devuelve la entidad Usuario o lanza una excepción.
        UUID propietarioId = usuarioRepository.findByEmail(emailAutenticado)
                                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"))
                                .getId();

        // 3. Buscar habitaciones por el ID del propietario
        // Necesitas añadir este método en tu HabitacionRepository
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


    
}
