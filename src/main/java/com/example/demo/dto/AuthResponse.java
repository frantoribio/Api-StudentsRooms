package com.example.demo.dto;

/**
 * DTO que representa la respuesta de autenticación.
 */
public class AuthResponse {
    
    private String email;
    private String token;
    private String rol;
    private String id;
    
    public AuthResponse(String email, String token, String rol, String id) {  
        this.email = email;
        this.token = token;
        this.rol = rol;
        this.id = id;
    }

    public AuthResponse(String email, String token) {  
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }  

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
