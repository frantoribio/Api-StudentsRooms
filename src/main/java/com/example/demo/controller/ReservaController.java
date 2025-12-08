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
import com.example.demo.dto.ReservaDTO;
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
    public List<ReservaDTO> getAllReservas() {
        List<ReservaDTO> reservas = reservaService.findAllDTO();
        logger.info("GET /api/reservas - listando todas las reservas: " + reservaService.findAll().size());
        return reservas;
    
    }

   @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable UUID id) {
        return reservaService.findDTOById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody Reserva reserva, Authentication authentication) {
        String username = authentication.getName();
        Optional<Usuario> usuarioOpt = usuarioService.findByEmail(username);
        reserva.setAlumno(usuarioOpt.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        logger.info("POST /api/reservas - creando reserva para usuario: " + username);
        Reserva nuevaReserva = reservaService.save(reserva);
        return ResponseEntity.ok(reservaService.toDTO(nuevaReserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable UUID id, @RequestBody Reserva reservaDetails) {
        return reservaService.findById(id)
                .map(reserva -> {
                    reserva.setAlumno(reservaDetails.getAlumno());
                    reserva.setFechaInicio(reservaDetails.getFechaInicio());
                    reserva.setFechaFin(reservaDetails.getFechaFin());
                    reserva.setEstadoReserva(reservaDetails.getEstadoReserva());
                    logger.info("PUT /api/reservas/" + id + " - actualizando reserva");
                    return ResponseEntity.ok(reservaService.toDTO(reservaDetails));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable UUID id) {
        return reservaService.findById(id)
                .map(reserva -> {
                    reservaService.deleteById(id);
                    logger.info("DELETE /api/reservas/" + id + " - eliminando reserva");
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/habitacion/{id}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorHabitacion(@PathVariable UUID id) {
        List<ReservaDTO> reservas = reservaService.obtenerReservasPorHabitacion(id);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/usuario{id}")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasPorUsuario(@PathVariable UUID id) {
        List<ReservaDTO> reservas = reservaService.obtenerReservasPorUsuario(id);
        if (reservas.isEmpty()) {
            logger.info("Usuario con id {} no tiene reservas.", id);
            return ResponseEntity.noContent().build();
            
        }
        logger.info("Usuario con id {} tiene {} reservas: {}", id, reservas.size(), reservas);
        return ResponseEntity.ok(reservas);
    }

}