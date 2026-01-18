package com.citas.usuarios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Sede")
public class Sede {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_sede")
    private Integer idSede;

    @Column(name="direccion")
    private String direccionSede;

    @Column(name="Telefono")
    private long telefonoSede;

    @Column(name="nombre_sede")
    private String nombreSede;

    public Integer getIdSede(){
        return idSede;
    }

    public String getDireccionSede(){
        return direccionSede;
    }

    public long getTelefonoSede(){
        return telefonoSede;
    }
    public String getNombreSede(){
        return nombreSede;
    }


    public void setIdSede(Integer idSede){
        this.idSede=idSede;
    }

    public void setDireccionSede(String direccionSede){
        this.direccionSede=direccionSede;
    }

    public void setTelefonoSede(long telefonoSede){
        this.telefonoSede=telefonoSede;
    }

    public void setDirrSede(String nombreSede){
        this.nombreSede=nombreSede;
    }
}
