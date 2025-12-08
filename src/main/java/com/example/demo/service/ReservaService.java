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
   
    


    public List<ReservaDTO> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(ReservaMapper::toDTO).toList();
    }

    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;

    }

     public List<ReservaDTO> findAllDTO() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(ReservaMapper::toDTO)
                .toList();
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

    public List<ReservaDTO> obtenerReservasPorHabitacion(UUID habitacionId){
        return reservaRepository.findByHabitacionId(habitacionId).stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    public List<ReservaDTO> obtenerReservasPorUsuario(UUID usuarioId){
        return reservaRepository.findByAlumnoId(usuarioId).stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    public ReservaDTO toDTO(Reserva reserva) {

        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setHabitacionId(reserva.getHabitacion().getId());
        dto.setAlumnoEmail(reserva.getAlumno() != null ? reserva.getAlumno().getEmail() : null);
        dto.setPropietarioEmail(reserva.getPropietario() != null ? reserva.getPropietario().getEmail() : null);
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setEstadoReserva(reserva.getEstadoReserva());

        return dto;
    }

    public Optional<ReservaDTO> findDTOById(UUID id) {
        return reservaRepository.findById(id)
                .map(this::toDTO);
    }

    

}
