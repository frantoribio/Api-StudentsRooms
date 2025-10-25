package com.example.demo.security;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.demo.model.Rol;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Clase utilitaria para manejar la creación y validación de tokens JWT.
 */     
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationMs;

    /**
     * Método para extraer el token JWT de la solicitud HTTP.
     */
    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
    }

    /**
     * Método para extraer el nombre de usuario del token JWT.  
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    /**
     * Método para validar el token JWT.
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token);
            return true;

        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Método para generar un token JWT basado en la autenticación.
     * @param auth
     * @return
     */
    public String generarToken(Authentication auth) {
        String rol = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));
        long now = System.currentTimeMillis();
        
        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("rol", rol)
                .setIssuedAt(new java.util.Date(now))
                .setExpiration(new java.util.Date(now + expirationMs))
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    /**
     * Método para extraer el email del token JWT.
     * @param token
     * @return
     */ 
    public String extraerEmail(String token) {
        // Implement logic to extract email from JWT token
        // Example using io.jsonwebtoken.Jwts:
        return Jwts.parserBuilder()
            .setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    /**
     * Método para validar el token JWT.
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validarToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
        final String email = extraerEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Método para verificar si el token JWT ha expirado.
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        final java.util.Date expiration = Jwts.parserBuilder()
            .setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
        return expiration.before(new java.util.Date());
    }

    /**
     * Método para extraer el rol del token JWT.
     * @param token
     * @return
     */
    public String extraerRol(String token) {
        // Implement logic to extract role from JWT token if stored in claims
        // Example using io.jsonwebtoken.Jwts:
        return (String) Jwts.parserBuilder()
            .setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("rol");
    }

    /**
     * Método para crear un token JWT con email y rol.
     * @param email
     * @param rol
     * @return
     */ 
    public String crearToken(String email, Rol rol) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
            .setSubject(email)
            .claim("rol", rol)
            .setIssuedAt(new java.util.Date(now))
            .setExpiration(new java.util.Date(now + expirationMs))
            .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

}
