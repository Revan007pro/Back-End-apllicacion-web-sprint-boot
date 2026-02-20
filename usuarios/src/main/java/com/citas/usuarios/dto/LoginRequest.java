package com.citas.usuarios.dto;

public class LoginRequest {
    private String nombre;
    private String password;
    private String roll;
    private String usuario;
    private long id;

    public Long getIdUser(){
        return id;
    }

    public String getUsuario(){
        return usuario;
    }

    public String getRoll(){
        return roll;
    }

    public void setUsuarioAtributo(String usario){
        this.usuario=usario;
    }

    public void setUsuario(String roll){
        this.roll=roll;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdUser(Long id){
        this.id=id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
