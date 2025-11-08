package com.example.demo.model;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String filename;
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;



    public Imagen(String filename, String url, Usuario usuario) {
        this.filename = filename;
        this.url = url;
        this.usuario = usuario;
    }
        
    public Imagen(String filename, String url) {
        this.filename = filename;
        this.url = url;
    }
    // Getters and setters
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
        
    

}
