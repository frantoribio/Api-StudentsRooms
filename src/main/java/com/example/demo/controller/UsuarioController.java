package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.RegistroResponse;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador REST para gestionar usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Lista todos los usuarios en forma de DTOs.
     * 
     * @return Lista de UsuarioDTO
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioDTO> listarUsuarios() {
        System.out.println("📣 Entrando a listarUsuarios()");
        return usuarioService.findAllDTO();
    }

    /**
     * Obtiene un usuario por su ID en forma de DTO.
     * 
     * @param id ID del usuario
     * @return UsuarioDTO si se encuentra, 404 si no
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable UUID id) {
        Optional<UsuarioDTO> usuario = usuarioService.findByIdDTO(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un usuario existente.
     * 
     * @param id ID del usuario a actualizar
     * @param usuarioDetails Detalles del usuario a actualizar
     * @return UsuarioDTO actualizado o 404 si no se encuentra
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioDTO usuarioDetails) {
        Optional<UsuarioDTO> usuario = usuarioService.findByIdDTO(id);
        if (usuario.isPresent()) {
            UsuarioDTO usuarioExistente = usuario.get();
            usuarioExistente.setNombre(usuarioDetails.getNombre());
            usuarioExistente.setEmail(usuarioDetails.getEmail());

            // Map DTO to entity
            Usuario usuarioEntity = new Usuario();
            usuarioEntity.setId(usuarioExistente.getId());
            usuarioEntity.setNombre(usuarioExistente.getNombre());
            usuarioEntity.setEmail(usuarioExistente.getEmail());

            // Save entity and map back to DTO
            Usuario saved = usuarioService.guardar(usuarioEntity);
            UsuarioDTO actualizado = new UsuarioDTO();
            actualizado.setId(saved.getId());
            actualizado.setNombre(saved.getNombre());
            actualizado.setEmail(saved.getEmail());

            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable UUID id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Registra un nuevo usuario.
     * 
     * @param dto Datos del usuario a registrar
     * @return Respuesta de registro con token JWT
     */
    @PostMapping("/registro")
    public ResponseEntity<RegistroResponse> registrar(@RequestBody UsuarioDTO dto) {
        RegistroResponse response = usuarioService.registrarUsuario(dto);
        return ResponseEntity.ok(response); // ← ahora devuelve JSON válido
    }

    @GetMapping("/raw")
    public List<Usuario> listarRaw() {
    return usuarioService.listarTodos();
}
    // Método duplicado eliminarUsuario(UUID id) eliminado para evitar errores de compilación.

}
