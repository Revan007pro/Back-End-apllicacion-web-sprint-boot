package com.citas.usuarios.dto;

public class PreciosRequest {
    private Integer idPrecios;
    private float valorPrecios;

    public PreciosRequest(){}

    public Integer getIDPrecios(){
        return idPrecios;
    }

    public float getValorPrecioss(){return  valorPrecios;}

    public void setIDPreccios(Integer idPrecios){this.idPrecios=idPrecios;}
    public void setValorPRecios(float valorPrecios){this.valorPrecios=valorPrecios;}
}
