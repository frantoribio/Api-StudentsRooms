package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;   
import com.example.demo.model.Reserva;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio para la entidad Reserva.
 */
public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    // Agregar métodos personalizados aquí si es necesario   
    List<Reserva> findByAlumnoId(UUID alumnoId);
    List<Reserva> findByPropietarioId(UUID propietarioId);
    List<Reserva> findByHabitacionId(UUID habitacionId);


}
