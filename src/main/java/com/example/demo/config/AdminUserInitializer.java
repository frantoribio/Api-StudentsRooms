package com.example.demo.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;

@Configuration
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner initAdminUser(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@alquilerapp.com";
            String adminPassword = "admin123";

            boolean adminNotExists = usuarioRepository.findAll()
                    .stream()
                    .noneMatch(u -> adminEmail.equals(u.getEmail()));
            if (adminNotExists) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setEmail(adminEmail);
                admin.setContraseña(passwordEncoder.encode(adminPassword)); // Usa una contraseña segura
                admin.setRol(Rol.ADMIN); // Asegúrate de que el rol esté definido correctamente

                usuarioRepository.save(admin);
                System.out.println("Usuario administrador creado.");
            } else {
                System.out.println("Usuario administrador ya existe.");
            }
        };
    }
}