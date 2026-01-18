package com.citas.usuarios.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) puede ser util despues
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private long id;

    @Column(name="apellidos")
    private String apellidos;

    @Column(name = "contrasenia")
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name="fecha_nacimiento")
    private String fecha_nacimiento;

    @Column(name = "telefono")
    private BigInteger telefono;

    @Column(name= "roll")
    private String roll;

    public long getId() {
        return id;
    }

    public String getRoll() {
        return roll;
    }
    public void setNewRoll(String roll){
        this.roll=roll;
    }

    public void setUsuario(String roll) {
        this.roll = roll;
    }
    public String getApellidos(){
        return apellidos;
    }
    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }
    public String getFechaNacimiento(){
        return fecha_nacimiento;
    }
    public void setFechaNacmiento(String fecha_nacimiento){
        this.fecha_nacimiento=fecha_nacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }
}

