package com.citas.usuarios.dto;

public class SedeReuest {
    private Integer sedeId;
    private String dirrSede;
    private long teleSede;
    private String sedeName;

    public Integer getSedeId(){return sedeId;}
    public String getDirrSede(){return dirrSede;}
    public long getTelefoSede(){return teleSede;}
    public String getSedeName(){return sedeName;}


    public void setSedeId(Integer sedeId){
        this.sedeId=sedeId;
    }
    public void setDirSede(String dirrSede){
        this.dirrSede=dirrSede;
    }
    public void setTeleSede(long teleSede){
        this.teleSede=teleSede;
    }

    public void setSedeName(String sedeName){
        this.sedeName=sedeName;
    }
    
}
