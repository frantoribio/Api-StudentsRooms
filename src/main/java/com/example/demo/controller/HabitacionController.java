package com.example.demo.controller;

import java.util.*;  
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Usuario;
import com.example.demo.service.HabitacionService;

import com.example.demo.dto.CrearHabitacionRequest;
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
    /*
    @PostMapping
    @PreAuthorize("hasRole('ADMIN', 'PROPIETARIO')")
    public Habitacion createHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.save(habitacion);
    }
    */

    @PostMapping
    @PreAuthorize("hasRole('ADMIN', 'PROPIETARIO')")
    public Habitacion createHabitacion(@RequestBody CrearHabitacionRequest request) {
    // Aquí mapeas el DTO a la Entidad y asignas el Propietario manualmente
    // desde el contexto de seguridad (JWT).
    
        Habitacion habitacion = new Habitacion();
        habitacion.setTitulo(request.getTitulo());
        habitacion.setCiudad(request.getCiudad());
        habitacion.setDireccion(request.getDireccion());
        habitacion.setPrecioMensual(request.getPrecioMensual());
        habitacion.setDescripcion(request.getDescripcion());
        habitacion.setImagenesUrl(request.getImagenesUrl());
        // 🔐 Obtener el email del usuario autenticado
        String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

        // 🔍 Buscar el usuario en la base de datos
        Usuario propietario = habitacionService.getUsuarioPorEmail(emailAutenticado);

        // ✅ Asignar el propietario
        habitacion.setPropietario(propietario);

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

    /**
     * Endpoint para el frontend de Android.
     * Devuelve las habitaciones del usuario autenticado (Rol PROPIETARIO).
     * RUTA: GET /api/habitaciones/propietario
     */
    @GetMapping("/propietario") // <-- ¡Este es el nuevo endpoint!
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<List<Habitacion>> getHabitacionesPropietario() {
        // Llama al servicio que utiliza SecurityContextHolder para el filtrado
        List<Habitacion> habitaciones = habitacionService.obtenerHabitacionesDelPropietarioAutenticado();
        
        return ResponseEntity.ok(habitaciones);
    }
     
}
