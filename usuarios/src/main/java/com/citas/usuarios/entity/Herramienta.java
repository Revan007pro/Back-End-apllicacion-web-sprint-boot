package com.citas.usuarios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "herramientas")
public class Herramienta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_herramienta")
    private Long idHerramienta;

    @Column(name = "titulo")
    private String tituloTool;

    @Column(name = "autor")
    private String autorTool;

    @Column(name = "estado")
    private String estadoTool;

    @Column(name = "disponible")
    private Boolean disponibleTool;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Usuario usuario;

    public Usuario getUserTool() {
        return usuario;
    }

    public String getEstadoTool() {
        return estadoTool;
    }

    public Boolean getDisponibleTool() {
        return disponibleTool;
    }

    public Long getIdHerameinta() {
        return idHerramienta;
    }

    public String getTituloTool() {
        return tituloTool;
    }

    public String getAutorTool() {
        return autorTool;
    }

    public void setIdHerramienta(Long idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public void setTituloTool(String tituloTool) {
        this.tituloTool = tituloTool;
    }

    public void setAutorTool(String autorTool) {
        this.autorTool = autorTool;
    }

    public void setEstatoTool(String estadoTool) {
        this.estadoTool = estadoTool;
    }

    public void setDisponibleTool(Boolean disponibleTool) {
        this.disponibleTool = disponibleTool;
    }

    public void setUserTool(Usuario usuario) {
        this.usuario = usuario;
    }

}
