package com.citas.usuarios.dto;

public class ClienteCita {
    private Long idPersona;
    private String nombreCliente;
    private Long idCitaCliente;


    public ClienteCita(Long idPersona,String nombreCliente, Long idCitaCliente){
        this.idPersona=idPersona;
        this.nombreCliente=nombreCliente;
        this.idCitaCliente=idCitaCliente;
    }
    public ClienteCita(){}

    public Long getIdCliente(){
        return idPersona;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }

    public Long getIdCitaCliente(){
        return idCitaCliente;
    }

    public void setClienteCita(Long idPersona){
        this.idPersona=idPersona;
    }

    public void setNombreCliente(String nombreCliente){
        this.nombreCliente=nombreCliente;
    }

    public void setIdCitaCliente(Long idCitaCliente){
        this.idCitaCliente=idCitaCliente;
    }
}
