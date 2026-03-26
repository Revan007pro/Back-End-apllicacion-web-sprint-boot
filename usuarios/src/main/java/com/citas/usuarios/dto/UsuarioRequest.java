package com.citas.usuarios.dto;

import java.math.BigInteger;

public class UsuarioRequest {
    private Long id_persona;
    private String nombreUser;
    private String apellidosUser;
    private String fechaNacimeinto;
    private String correoUser;
    private BigInteger newTelefono;
    private String contraseniaUser;
    public UsuarioRequest(Long id_persona, String nombreUser,String apellidosUser,String fechaNacimeinto,String correoUser,BigInteger newTelefono,String contraseniaUser){
        this.apellidosUser=apellidosUser;
        this.id_persona=id_persona;
        this.nombreUser=nombreUser;
        this.fechaNacimeinto=fechaNacimeinto;
        this.correoUser=correoUser;
        this.newTelefono=newTelefono;
        this.contraseniaUser=contraseniaUser;
    }
    public UsuarioRequest(){}

    public String getNombreUser(){return nombreUser;}
    public Long getIdPersona(){return id_persona;}
    public String getApellidosUser(){return apellidosUser;}
    public String getFechaUser(){return fechaNacimeinto;}
    public String getCorreoUser(){return correoUser;}
    public String getContraseniaUser(){return contraseniaUser;}
    public BigInteger getNewTelefono(){return newTelefono;} 

    public void setApellidosUser(String apellidosUser){this.apellidosUser=apellidosUser;}
    public void setIdPersona(Long id_persona){this.id_persona=id_persona;}
    public void setNombreoUser(String nombreUser){this.nombreUser=nombreUser;}
    public void setFechaUser(String fechaNacimeinto){this.fechaNacimeinto=fechaNacimeinto;}
    public void setCorreoUser(String correoUser){this.correoUser=correoUser;}
    public void setTelefonoUser(BigInteger newTelefono){this.newTelefono=newTelefono;}
    public void setContraseniaUser(String contraseniaUser){this.contraseniaUser=contraseniaUser;}


}
