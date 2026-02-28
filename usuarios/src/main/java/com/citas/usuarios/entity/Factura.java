package com.citas.usuarios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Factura")
public class Factura {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_factura")
    private Long idFactura;

    @OneToOne
    @JoinColumn(name="id_cita")
    private Citas citaFactu;

    @Column(name="fecha_emicion")
    private String fechaEmicion;

    @Column(name="total")
    private float totalFactura;


    @OneToOne
    @JoinColumn(name="id_precio",referencedColumnName=("id_precio"))
    private Precios precio;


    @OneToOne
    @JoinColumn(name="id_empresa",referencedColumnName=("id_empresa"))
    private Empresa empresa;


    public Long getIdFactura(){ return idFactura; }
    public Citas getCitaFactu(){ return citaFactu; }
    public String getFechaEmicion(){ return fechaEmicion; }
    public float getTotalFactura(){ return totalFactura; }
    public Precios getPrecio(){ return precio; }
    public Empresa getEmpresa(){ return empresa; }


    public void setCitaFactura(Citas citaFatu){ this.citaFactu=citaFatu; }
    public void setFechaEmicion(String fechaEmicion){ this.fechaEmicion=fechaEmicion; }
    public void setTotalFactura(float totalFactura){ this.totalFactura=totalFactura; }
    public void setPrecio(Precios precio){ this.precio=precio; }
    public void setEmpresa(Empresa empresa){ this.empresa=empresa; }
}
