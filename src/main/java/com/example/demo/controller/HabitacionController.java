package com.example.demo.controller;

import java.util.*;  
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.model.Habitacion;
import com.example.demo.service.HabitacionService;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    
    @GetMapping 
    public List<Habitacion> getAllHabitaciones() {
        return habitacionService.findAll();      
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> getHabitacionById(@PathVariable UUID id) {
        Optional<Habitacion> habitacion = habitacionService.findById(id);
        return habitacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN', 'PROPIETARIO')")
    public Habitacion createHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.save(habitacion);
    }

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
