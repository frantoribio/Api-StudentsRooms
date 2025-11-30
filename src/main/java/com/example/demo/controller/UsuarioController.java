package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    //private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        //this.passwordEncoder = passwordEncoder;
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
        logger.info("Entrando a listarUsuarios()");
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
        logger.info("GET /api/usuarios/" + id + " - solicitando usuario por ID");
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un usuario existente.
     * 
     * @param id ID del usuario a actualizar
     * @param usuarioDetails Detalles del usuario a actualizar
     * @return UsuarioDTO actualizado o 404 si no se encuentra
     */
   /*  @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioDTO usuarioDetails) {
        Optional<UsuarioDTO> usuario = usuarioService.findByIdDTO(id);
        if (usuario.isPresent()) {
            UsuarioDTO usuarioExistente = usuario.get();
            usuarioExistente.setNombre(usuarioDetails.getNombre());
            usuarioExistente.setEmail(usuarioDetails.getEmail());
            
            if (usuarioDetails.getContraseña() != null && !usuarioDetails.getContraseña().isBlank()) {
            usuarioExistente.setContraseña(
                passwordEncoder.encode(usuarioDetails.getContraseña())
            );
        }


            //usuarioExistente.setContraseña(usuarioDetails.getContraseña());
            usuarioExistente.setRol(usuarioDetails.getRol());

            // Map DTO to entity
            Usuario usuarioEntity = new Usuario();
            usuarioEntity.setId(usuarioExistente.getId());
            usuarioEntity.setNombre(usuarioExistente.getNombre());
            usuarioEntity.setEmail(usuarioExistente.getEmail());
            usuarioEntity.setContraseña(usuarioExistente.getContraseña());
            usuarioEntity.setRol(usuarioExistente.getRol());

            // Save entity and map back to DTO
            Usuario saved = usuarioService.guardar(usuarioEntity);
            UsuarioDTO actualizado = new UsuarioDTO();
            actualizado.setId(saved.getId());
            actualizado.setNombre(saved.getNombre());
            actualizado.setEmail(saved.getEmail());
            actualizado.setContraseña(saved.getContraseña());
            actualizado.setRol(saved.getRol());

            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable UUID id,
            @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizaUsuario(id, usuario);
        if (actualizado == null) {
            logger.info("PUT /api/usuarios/" + id + " - usuario no encontrado para actualizar");
            return ResponseEntity.notFound().build();
        }
        logger.info("PUT /api/usuarios/" + id + " - actualizando usuario");
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable UUID id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            logger.info("DELETE /api/usuarios/" + id + " - eliminando usuario");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("DELETE /api/usuarios/" + id + " - usuario no encontrado para eliminar");
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
        logger.info("POST /api/usuarios/registro - registrando usuario: " + dto.getEmail());
        return ResponseEntity.ok(response); 
    }

    @GetMapping("/raw")
    public List<Usuario> listarRaw() {
    return usuarioService.listarTodos();
    }
}
