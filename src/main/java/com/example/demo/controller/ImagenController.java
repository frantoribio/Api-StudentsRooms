package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.config.UploadProperties;
import com.example.demo.model.Imagen;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.ImagenRepository;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {
    private final UploadProperties uploadProperties;
    private final UsuarioRepository usuarioRepository;
    private final ImagenRepository imagenRepository;
    private static final Logger logger = LoggerFactory.getLogger(ImagenController.class);

    public ImagenController(UploadProperties uploadProperties, UsuarioRepository usuarioRepository, ImagenRepository imagenRepository) {
        this.uploadProperties = uploadProperties;
        this.usuarioRepository = usuarioRepository;
        this.imagenRepository = imagenRepository;
    }
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> subirImagen(
        @RequestParam("image") MultipartFile file,
        @RequestParam String userId) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Archivo vacío"));
            }
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadProperties.getDir());
        
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);


            String url = uploadProperties.getBaseUrl() + filename;

            Optional<Usuario> usuario = usuarioRepository.findByEmail(userId);
            Imagen imagen = new Imagen(filename, url);
            usuario.ifPresent(imagen::setUsuario);
            imagenRepository.save(imagen);
            logger.info("POST /api/imagenes - imagen subida para usuario: " + userId);

            return ResponseEntity.ok(Map.of("url", url));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al guardar imagen: " + e.getMessage()));
        }
    }
}