package com.example.demo.service;

import org.springframework.stereotype.Service;   
import com.example.demo.model.Habitacion;
import com.example.demo.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> findById(UUID id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion save(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void deleteById(UUID id) {
        habitacionRepository.deleteById(id);
    }
}
