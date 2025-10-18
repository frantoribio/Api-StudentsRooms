package com.example.demo.service;

import com.example.demo.dto.HabitacionDTO; 
import com.example.demo.model.Habitacion;
import com.example.demo.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;
    
    // Inyectamos el nuevo Mapper
    @Autowired
    private HabitacionMapper habitacionMapper; 

    // MODIFICADO: Ahora devuelve List<HabitacionDTO>
    public List<HabitacionDTO> findAllDTO() {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        return habitacionMapper.toDTO(habitaciones); // Usamos el mapper
    }

    // MODIFICADO: Ahora devuelve Optional<HabitacionDTO>
    public Optional<HabitacionDTO> findByIdDTO(UUID id) {
        return habitacionRepository.findById(id)
                                   .map(habitacionMapper::toDTO); // Mapeamos si está presente
    }

    // NUEVO: Devuelve Optional<Habitacion> para el controlador
    public Optional<Habitacion> findById(UUID id) {
        return habitacionRepository.findById(id);
    }

    // Mantenemos save para que el controlador pueda persistir la entidad,
    // pero idealmente deberías recibir un HabitacionCreationDTO aquí.
    public Habitacion save(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void deleteById(UUID id) {
        habitacionRepository.deleteById(id);
    }
    
}
