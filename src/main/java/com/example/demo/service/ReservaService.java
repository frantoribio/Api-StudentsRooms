package com.example.demo.service;
    
import com.example.demo.dto.ReservaDTO;
import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.UsuarioRepository;

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

    @Autowired
    private ReservaMapper reservaMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
        this.usuarioRepository = usuarioRepository;
    }

     public List<ReservaDTO> findAllDTO() {
        return reservaMapper.toDTOList(reservaRepository.findAll());
    }

    public Optional<Reserva> findById(UUID id) {
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteById(UUID id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> findAll() {
    return reservaRepository.findAll();
    }

}

