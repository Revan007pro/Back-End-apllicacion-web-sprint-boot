package com.citas.usuarios.dto;

public class FacturaRequest {
    private Long idFactura;
    private Long idCita;
    private String fechaEmi;
    private float totalFactura;
    private Long idPrecio;
    private Long idEmpresa;

    public FacturaRequest(){}

    public Long getIdFactura(){return idFactura;}
    public Long getIdCita(){return idCita;}
    public String getFechaEmi(){return fechaEmi;}
    public float getTotalFactu(){return totalFactura;}
    public Long getIdPrecio(){ return idPrecio; }
    public Long getIDEmpresa(){return idEmpresa;}

    public void setIDFactura(Long idFactura){this.idFactura=idFactura;}
    public void setIDCita(Long idCita){this.idCita=idCita;}
    public void setFehaEM(String fechaEmi){this.fechaEmi=fechaEmi;}
    public void setTotalFAc(float totalFactura){this.totalFactura=totalFactura;}
    public void setIdPrecio(Long idPrecio){ this.idPrecio = idPrecio; }
    public void setIDEmpresa(Long idEmpresa){this.idEmpresa=idEmpresa;}
}
