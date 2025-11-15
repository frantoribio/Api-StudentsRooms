package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Imagen;

import java.util.List;
import java.util.UUID;

public interface ImagenRepository extends CrudRepository<Imagen, UUID> {
    // You can add custom query methods here if needed
    List<Imagen> findByUsuarioId(UUID usuarioId);
    void deleteByUsuarioId(UUID usuarioId);


    
}
