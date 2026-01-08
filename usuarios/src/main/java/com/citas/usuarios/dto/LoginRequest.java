package com.citas.usuarios.dto;

public class LoginRequest {
    private String nombre;
    private String password;
    private String roll;

    public String getRoll(){
        return roll;
    }

    public void setUsuario(String roll){
        this.roll=roll;
    }

    public String getNombre() {
        return nombre;
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
