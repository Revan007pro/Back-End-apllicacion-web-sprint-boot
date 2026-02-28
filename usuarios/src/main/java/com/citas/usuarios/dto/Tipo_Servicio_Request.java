package com.citas.usuarios.dto;

public class Tipo_Servicio_Request {
    private Integer idTServicio;
    private String rollTServicio;
    private String descripcionTServicio;
    private Integer idPrecio;

    public Tipo_Servicio_Request(Integer idTServicio,String rollTServicio,String descripcionTServicio, Integer idPrecio){
        this.idTServicio=idTServicio;
        this.rollTServicio=rollTServicio;
        this.descripcionTServicio=descripcionTServicio;
        this.idPrecio=idPrecio;
    }
    public Tipo_Servicio_Request(){}

    public Integer getIDTServicio(){return  idTServicio;}
    public String getrollTipoServicio(){return rollTServicio;}
    public String getDescripTServicio(){return descripcionTServicio;}
    public Integer getIDPrecioTS(){return idPrecio;}


    public void setIDTServicio(Integer idTServicio){this.idTServicio=idTServicio;}
    public void setrollTS(String rollTServicio){this.rollTServicio=rollTServicio;}
    public void setDescripTServicio(String descripcionTServicio){this.descripcionTServicio=descripcionTServicio;}
    public void setPrecioTS(Integer idPrecio){this.idPrecio=idPrecio;}


}
