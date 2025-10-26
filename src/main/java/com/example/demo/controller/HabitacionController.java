package com.example.demo.controller;

import java.util.*;  
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.demo.model.Habitacion;
import com.example.demo.service.HabitacionService;
import com.example.demo.dto.HabitacionDTO;

/**
 * Controlador REST para gestionar habitaciones.
 */
@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    /**
     * Lista todas las habitaciones como DTOs.
     * 
     * @return Lista de HabitacionDTO
     */
    @GetMapping 
    public List<HabitacionDTO> getAllHabitaciones() {
        return habitacionService.findAllDTO();      
    }

    /**
     * Obtiene una habitación por su ID como DTO.
     * 
     * @param id ID de la habitación
     * @return HabitacionDTO si se encuentra, 404 si no
     */
    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDTO> getHabitacionById(@PathVariable UUID id) {
        Optional<HabitacionDTO> habitacion = habitacionService.findByIdDTO(id);
        return habitacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva habitación.
     * 
     * @param habitacion Datos de la habitación a crear
     * @return Habitación creada
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN', 'PROPIETARIO')")
    public Habitacion createHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.save(habitacion);
    }

    /**
     * Actualiza una habitación existente.
     * 
     * @param id ID de la habitación a actualizar
     * @param habitacionDetails Detalles de la habitación a actualizar
     * @return Habitación actualizada o 404 si no se encuentra
     */
    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> updateHabitacion(@PathVariable UUID id, @RequestBody Habitacion habitacionDetails) {
        Optional<Habitacion> habitacion = habitacionService.findById(id);
        if (habitacion.isPresent()) {
            Habitacion updatedHabitacion = habitacion.get();
            updatedHabitacion.setTitulo(habitacionDetails.getTitulo());
            updatedHabitacion.setDescripcion(habitacionDetails.getDescripcion());
            updatedHabitacion.setPrecioMensual(habitacionDetails.getPrecioMensual());
            updatedHabitacion.setCiudad(habitacionDetails.getCiudad());
            updatedHabitacion.setPropietario(habitacionDetails.getPropietario());
            updatedHabitacion.setImagenesUrl(habitacionDetails.getImagenesUrl());
            updatedHabitacion.setDireccion(habitacionDetails.getDireccion());
            updatedHabitacion.setReservas(habitacionDetails.getReservas());
            
            return ResponseEntity.ok(habitacionService.save(updatedHabitacion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina una habitación por su ID.
     * 
     * @param id ID de la habitación a eliminar
     * @return 204 si se elimina, 404 si no se encuentra
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable UUID id) {
        Optional<Habitacion> habitacion = habitacionService.findById(id);
        if (habitacion.isPresent()) {
            habitacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     
}
