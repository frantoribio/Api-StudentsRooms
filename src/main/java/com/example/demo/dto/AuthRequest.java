package com.example.demo.dto;

/**
 * DTO que representa la solicitud de autenticación.
 */
public class AuthRequest {
    
    private String email;
    private String contraseña;
    private String rol;

    public AuthRequest(String email, String contraseña, String rol) {
        this.email = email;
        this.contraseña = contraseña;
        this.rol = rol;
    }
      
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) { 
        this.email = email;
    } 
    
    public String getContraseña() { 
        return contraseña; 
    }
    
    public void setContraseña(String contraseña) { 
        this.contraseña = contraseña; 
    }

    public String getRol() {
        return rol;
    } 
                
}
