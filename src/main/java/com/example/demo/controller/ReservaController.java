package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.dto.ReservaDTO;
import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.service.ReservaService;
import com.example.demo.service.UsuarioService;

/**
 * Controlador REST para gestionar reservas.
 */
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
   
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Lista todas las reservas.
     * 
     * @return Lista de reservas
     */
    @GetMapping    
    public List<Reserva> getAllReservas() {
        return reservaService.findAll();
    
    }

    /**
     * Obtiene una reserva por su ID.
     * 
     * @param id ID de la reserva
     * @return Reserva si se encuentra, 404 si no
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable UUID id) {
        Optional<Reserva> reserva = reservaService.findById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva reserva.
     * 
     * @param reserva Datos de la reserva a crear
     * @return Reserva creada
     */
    /* @PostMapping
    @PreAuthorize("hasRole('ADMIN', 'PROPIETARIO', 'ALUMNO')")
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaService.save(reserva);
    } */

    /**
     * Crea una nueva reserva asociada al usuario autenticado.
     * @param reserva
     * @param authentication
     * @return
     */
    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva, Authentication authentication) {
        String username = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioService.findByEmail(username);
        reserva.setAlumno(usuarioOpt.orElse(null));
        return reservaService.save(reserva);
    } 

   /*  @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva, Authentication authentication) {
        // Buscar el usuario autenticado por email (sub del JWT)
        Usuario usuario = usuarioService.findByEmail(authentication.getName());
        reserva.setAlumno(usuario);
        return reservaService.save(reserva);
    }
 */


    /**
     * Actualiza una reserva existente.
     * 
     * @param id ID de la reserva a actualizar
     * @param reservaDetails Detalles de la reserva a actualizar
     * @return Reserva actualizada o 404 si no se encuentra
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable UUID id, @RequestBody Reserva reservaDetails) {
        Optional<Reserva> reserva = reservaService.findById(id);
        if (reserva.isPresent()) {
            Reserva updatedReserva = reserva.get();
            updatedReserva.setAlumno(reservaDetails.getAlumno());
            updatedReserva.setFechaInicio(reservaDetails.getFechaInicio());
            updatedReserva.setFechaFin(reservaDetails.getFechaFin());
            updatedReserva.setEstadoReserva(reservaDetails.getEstadoReserva());
            
            return ResponseEntity.ok(reservaService.save(updatedReserva));
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Elimina una reserva por su ID.
     * 
     * @param id ID de la reserva a eliminar
     * @return 204 si se elimina, 404 si no se encuentra
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable UUID id) {
        Optional<Reserva> reserva = reservaService.findById(id);
        if (reserva.isPresent()) {
            reservaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    } 

   /*  @PostMapping
    public ReservaDTO crearReserva(@RequestBody Reserva reserva, Authentication auth) {
        Reserva saved = reservaService.save(reserva);
        return new ReservaDTO(
            saved.getId(),
            saved.getHabitacion().getId(),
            saved.getAlumno().getId(),
            saved.getFechaInicio(),
            saved.getFechaFin(),
            saved.getEstadoReserva()
        );
    } */

}
