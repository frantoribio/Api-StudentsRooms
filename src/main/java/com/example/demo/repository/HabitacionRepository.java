package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;   
import com.example.demo.model.Habitacion;
import java.util.UUID;

public interface HabitacionRepository extends JpaRepository<Habitacion, UUID> {
    // Puedes agregar métodos personalizados aquí si es necesario
}

    

