package com.citas.usuarios.dto;

import java.math.BigInteger;

public class SaveRequest {

    public String new_nombres;
    public String new_roll;
    public String new_apellidos;

   // @JsonFormat(pattern = "dd-MM-yyyy")
    public String fecha_nacimiento;
    public String new_correo;
    public String new_contrasenia;
    public String confirmar_new_contrasenia;
    public BigInteger new_telefono;

    public String getNewRoll(){
        return new_roll;
    }
    public void setNewRoll(String new_roll){
        this.new_roll=new_roll;
    }

    public String getNewUsuario(){
        return new_nombres;
    }
    public void setNewUsuario(String new_nombres){
        this.new_nombres=new_nombres;
    }
    public String getNewApellidos(){
        return new_apellidos;
    }
    public void setNewApellidos(String new_apellidos){
        this.new_apellidos=new_apellidos;
    }
    public String getNewfecha_nacimiento(){
        return fecha_nacimiento;
    }
    public void setNewFecha_Nacimiento(String fecha_nacimeinto){
        this.fecha_nacimiento=fecha_nacimeinto;
    }
    public String getNewCorreo(){
        return new_correo;
    }
    public void setNewCorreo(String new_correo){
        this.new_correo=new_correo;
    }
    public String getNewContrasenia(){
        return new_contrasenia;
    }
    public void setNewContrasenia(String new_contrasenia){
        this.new_contrasenia=new_contrasenia;
    }
    public String getNewConfirmar(){
        return confirmar_new_contrasenia;
    }
    public void setNewConfirmar(String confirmar_new_contrasenia){
        this.confirmar_new_contrasenia=new_contrasenia;
    }
    public BigInteger getNewTelefono(){
        return new_telefono;
    }
    public void setNewTelefono(BigInteger new_telefono){
        this.new_telefono=new_telefono;
    }

}
