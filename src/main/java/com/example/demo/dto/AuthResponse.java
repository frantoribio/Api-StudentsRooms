package com.example.demo.dto;

public class AuthResponse {
    
    private String email;
    private String token;
    private String rol;
    
    public AuthResponse(String email, String token, String rol) {  
        this.email = email;
        this.token = token;
        this.rol = rol;
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

}
