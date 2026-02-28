package com.citas.usuarios.dto;

import java.math.BigInteger;

public class EmpresaRequest {
    private Integer idEmpresa;
    private String nombreEmpresa;
    private BigInteger nitEmpresa;
    private String direEmpresa;

    public EmpresaRequest(Integer idEmpresa,String nombreEmpresa,BigInteger nitEmpresa,String direEmpresa){
        this.idEmpresa=idEmpresa;
        this.nombreEmpresa=nombreEmpresa;
        this.nitEmpresa=nitEmpresa;
        this.direEmpresa=direEmpresa;

    }
    public EmpresaRequest(){}

    public Integer getIDDEmpresa(){return idEmpresa;}
    public String getNombreEmpresA(){return nombreEmpresa;}
    public BigInteger getNIITEmpresa(){return nitEmpresa;}
    public String getDireEmpresa(){return direEmpresa;}

    public void setIDDEmpresa(Integer idEmpresa){this.idEmpresa=idEmpresa;}
    public void setNombreEmpreA(String nombreEmpresa){this.nombreEmpresa=nombreEmpresa;}
    public void setNIItEmpresa(BigInteger nitEmpresa){this.nitEmpresa=nitEmpresa;}
    public void setDireEmpresa(String direEmpresa){this.direEmpresa=direEmpresa;}
    
}
