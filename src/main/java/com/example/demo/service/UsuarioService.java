package com.example.demo.service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.RegistroResponse;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ImagenRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JwtUtil;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para gestionar operaciones relacionadas con usuarios.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ImagenRepository imagenRepository;

    /**
     * Constructor para inyectar dependencias.
     * 
     * @param usuarioRepository Repositorio de usuarios.
     * @param usuarioMapper Mapper para convertir entre entidades y DTOs.
     * @param passwordEncoder Codificador de contraseñas.
     * @param jwtUtil Utilidad para manejar tokens JWT.
     */
    public UsuarioService(UsuarioRepository usuarioRepository,
        UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder,
        JwtUtil jwtUtil, ImagenRepository imagenRepository) {

        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.imagenRepository = imagenRepository;
    }

    /**
     * Obtiene una lista de todos los usuarios en forma de DTOs.
     * 
     * @return Lista de UsuarioDTO.
     */
    public List<UsuarioDTO> findAllDTO() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        System.out.println("👥 Usuarios encontrados: " + usuarios.size());

        return usuarioMapper.listToDTO(usuarios);
    }

    /**
     * Busca un usuario por su ID y lo devuelve como DTO.
     * 
     * @param id ID del usuario.
     * @return Optional con el UsuarioDTO si se encuentra, vacío en caso contrario.
     */
    public Optional<UsuarioDTO> findByIdDTO(UUID id) {
        return usuarioRepository.findById(id)
                                .map(usuarioMapper::toDTO);
    }

    /**
     * Busca un usuario por su ID.
     * 
     * @param id ID del usuario.
     * @return Optional con el Usuario si se encuentra, vacío en caso contrario.
     */
    public Optional<Usuario> findById(UUID id) {
        return usuarioRepository.findById(id);
    }


    /**
     * Guarda un usuario en el repositorio.
     * 
     * @param usuario Usuario a guardar.
     * @return Usuario guardado.
     */
    public Usuario guardar(Usuario usuario) {
        //String contraseñaEncriptada = passwordEncoder.encode(usuario.getContraseña());
        //usuario.setContraseña(contraseñaEncriptada);
        return usuarioRepository.save(usuario);
    }

    /**
     * Elimina un usuario por su ID.
     * 
     * @param id ID del usuario a eliminar.
     */
    public void eliminarPorId(UUID id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Lista todos los usuarios.
     * 
     * @return Lista de usuarios.
     */
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Registra un nuevo usuario y genera un token JWT.
     * 
     * @param dto Datos del usuario a registrar.
     * @return Respuesta de registro con email, token y rol.
     */
    public RegistroResponse registrarUsuario(UsuarioDTO dto) {

        String contraseñaEncriptada = passwordEncoder.encode(dto.getContraseña());

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setContraseña(contraseñaEncriptada);
        guardar(usuario);

        String token = jwtUtil.crearToken(usuario.getEmail(), usuario.getRol());
        
        return new RegistroResponse(usuario.getEmail(), token, usuario.getRol().name());
    }
    
    @Transactional
    public boolean eliminarUsuario(UUID id) {
        if (usuarioRepository.existsById(id)) {
            imagenRepository.deleteByUsuarioId(id);
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Usuario> actualizarUsuario (UUID id, Usuario usuarioDetails) {
        String contraseñaEncriptada = passwordEncoder.encode(usuarioDetails.getContraseña());
        usuarioDetails.setContraseña(contraseñaEncriptada);
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setContraseña(usuarioDetails.getContraseña());
            usuario.setRol(usuarioDetails.getRol());
            return usuarioRepository.save(usuario);
        });
    }

}
