package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;   
import com.example.demo.model.Reserva;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    // Agregar métodos personalizados aquí si es necesario
}