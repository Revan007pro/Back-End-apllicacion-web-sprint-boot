package com.citas.usuarios.dto;

public class FacturaRequest {
    private Long idFactura;
    private Long idCita;
    private String fechaEmi;
    private float totalFactura;
    private Long idPrecio;
    private Long idEmpresa;

    public FacturaRequest() {
    }

    // Getters corregidos
    public Long getIdFactura() {
        return idFactura;
    }

    public Long getIdCita() {
        return idCita;
    }

    public String getFechaEmi() {
        return fechaEmi;
    }

    public float getTotalFactura() {
        return totalFactura;
    }

    public Long getIdPrecio() {
        return idPrecio;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    } // Cambiado de getIDEmpresa a getIdEmpresa

    // Setters corregidos
    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public void setFechaEmi(String fechaEmi) {
        this.fechaEmi = fechaEmi;
    }

    public void setTotalFactura(float totalFactura) {
        this.totalFactura = totalFactura;
    }

    public void setIdPrecio(Long idPrecio) {
        this.idPrecio = idPrecio;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}