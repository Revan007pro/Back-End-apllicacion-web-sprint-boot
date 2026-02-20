package com.citas.usuarios.dto;

public class CitasReprogramar {

    private String fecha;
    private String horainicio;

    public CitasReprogramar(){}

    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getHorainicio(){
        return horainicio;
    }

    public void setHorainicio(String horainicio){
        this.horainicio = horainicio;
    }
}