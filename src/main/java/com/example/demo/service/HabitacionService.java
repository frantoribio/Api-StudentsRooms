package com.example.demo.service;

import com.example.demo.dto.HabitacionDTO; 
import com.example.demo.model.Habitacion;
import com.example.demo.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
