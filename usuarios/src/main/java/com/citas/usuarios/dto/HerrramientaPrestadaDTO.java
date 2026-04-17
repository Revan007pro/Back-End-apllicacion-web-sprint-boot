package com.citas.usuarios.dto;

import com.citas.usuarios.entity.Herramienta;
import com.citas.usuarios.entity.Usuario;

public class HerrramientaPrestadaDTO {

    private Long idHerramienta;
    private String titulo;
    private String autor;
    private String estado;
    private Boolean disponible;
    private Usuario usuario;

    public HerrramientaPrestadaDTO(Herramienta tool) {
        this.idHerramienta = tool.getIdHerameinta();
        this.titulo = tool.getTituloTool();
        this.autor = tool.getAutorTool();
        this.estado = tool.getEstadoTool();
        this.disponible = tool.getDisponibleTool();
        this.usuario = tool.getUserTool();
    }

    public HerrramientaPrestadaDTO() {
    }

    public Long getIdHerramienta() {
        return idHerramienta;
    }

    public String getAutorTool() {
        return autor;
    }

    public String getTituloTool() {
        return titulo;
    }

    public String getEstadoTool() {
        return estado;
    }

    public Boolean getDisponibleTool() {
        return disponible;
    }

    public Usuario getUserTool() {
        return usuario;
    }

    public void setIdHerramienta(Long idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public void setAutorTool(String autor) {
        this.autor = autor;
    }

    public void setTituloTool(String titulo) {
        this.titulo = titulo;
    }

    public void setEstadoTool(String estado) {
        this.estado = estado;
    }

    public void setDisponibleTool(boolean disponible) {
        this.disponible = disponible;
    }

    public void setUserTool(Usuario usuario) {
        this.usuario = usuario;
    }

}