package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);

    /**
     * Lista todas las reservas.
     * 
     * @return Lista de reservas
     */
    @GetMapping    
    public List<Reserva> getAllReservas() {
        logger.info("GET /api/reservas - listando todas las reservas: " + reservaService.findAll().size());
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
        logger.info("GET /api/reservas/" + id + " - solicitando reserva por ID");
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

  

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
        logger.info("POST /api/reservas - creando reserva para usuario: " + username);
        return reservaService.save(reserva);
    } 


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
            logger.info("PUT /api/reservas/" + id + " - actualizando reserva");
            return ResponseEntity.ok(reservaService.save(updatedReserva));
            
        } else {
            logger.info("PUT /api/reservas/" + id + " - reserva no encontrada para actualizar");
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
            logger.info("DELETE /api/reservas/" + id + " - eliminando reserva");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("DELETE /api/reservas/" + id + " - reserva no encontrada para eliminar");
            return ResponseEntity.notFound().build();
        }
    } 

}
