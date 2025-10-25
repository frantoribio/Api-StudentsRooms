package com.example.demo.service;
    
import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para gestionar las reservas.
 */
@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    /**
     * Obtiene todas las reservas.
     * 
     * @return Lista de reservas.
     */
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    /**
     * Obtiene una reserva por su ID.
     * 
     * @param id
     * @return Reserva encontrada o vacía.
     */
    public Optional<Reserva> findById(UUID id) {
        return reservaRepository.findById(id);
    }

    /**
     * Guarda una reserva.
     * 
     * @param reserva
     * @return Reserva guardada.
     */
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
    
    /**
     * Elimina una reserva por su ID.
     * 
     * @param id
     */
    public void deleteById(UUID id) {
        reservaRepository.deleteById(id);
    }

}
