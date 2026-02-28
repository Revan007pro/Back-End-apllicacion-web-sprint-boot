package com.citas.usuarios.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_empresa")
    private Integer idEmpresa;

    @Column(name="nombre_empresa")
    private String nombreEmpresa;

    @Column(name="nit")
    private BigInteger nitEmpresa;

    @Column(name="direccion")
    private String direcEmpresa;

    
    public Integer getIDEmpresa(){return idEmpresa;}
    public String getNombreEmpresa(){return nombreEmpresa;}
    public BigInteger getNitEmoresa(){return nitEmpresa;}
    public String getDireccEmpresa(){return direcEmpresa;}

    public void setIDEmpresaEs(Integer idEmpresa){this.idEmpresa=idEmpresa;}
    public void setNomnreEmpresa(String nombreEmpresa){this.nombreEmpresa=nombreEmpresa;}
    public void setNitEmpresa(BigInteger nitEmpresa){this.nitEmpresa=nitEmpresa;}
}
