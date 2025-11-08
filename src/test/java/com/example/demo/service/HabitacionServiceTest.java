package com.example.demo.service;

import com.example.demo.dto.CrearHabitacionDto;
import com.example.demo.model.Habitacion;
import com.example.demo.model.Usuario;
import com.example.demo.repository.HabitacionRepository;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HabitacionServiceTest {

    @Test
    void testCrearHabitacionAsignaPropietario() {
        // Arrange
        HabitacionRepository habitacionRepository = mock(HabitacionRepository.class);
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);

        HabitacionService service = new HabitacionService(habitacionRepository, usuarioRepository);

        UUID propietarioId = UUID.randomUUID();
        Usuario propietario = new Usuario();
        propietario.setId(propietarioId);
        propietario.setEmail("test@example.com");

        when(usuarioRepository.findById(propietarioId)).thenReturn(Optional.of(propietario));

        CrearHabitacionDto dto = new CrearHabitacionDto();
        dto.setTitulo("Habitación luminosa");
        dto.setCiudad("Madrid");
        dto.setDireccion("Calle Falsa 123");
        dto.setPrecioMensual(new BigDecimal("500.00"));
        dto.setDescripcion("Muy cómoda y céntrica");
        dto.setPropietarioId(propietarioId);

        Habitacion habitacionEsperada = new Habitacion();
        habitacionEsperada.setPropietario(propietario);

        when(habitacionRepository.save(any(Habitacion.class))).thenReturn(habitacionEsperada);

        // Act
        Habitacion resultado = service.crearHabitacion(dto);

        // Assert
        assertNotNull(resultado);
        assertNotNull(resultado.getPropietario());
        assertEquals(propietarioId, resultado.getPropietario().getId());
    }
}