package com.citas.usuarios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CitasRequest {

    private long id;

    private String especialidad;
    private Integer idCliente;
    

    @JsonProperty("nombreEespecialista")
    private String nombreEspecialista;
   // private String nombreSede;
    private String fecha;
    private Integer sedeId;

    private Long idEmpleado;

public Long getIdEmpleado() {
    return idEmpleado;
}


    @JsonProperty("horaInicio")
    private String horaInicio;

    @JsonProperty("horaFinal")
    private String horaFinal;

    public long getId(){
        return id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombreEspecialista() {
    return nombreEspecialista;
}

    public Integer getSedeId(){return sedeId;}

  /*   public String getNombreSede() {
        return nombreSede;
    } */

    public String getFecha() {
        return fecha;
    }

    public String getHorainicio() {
        return horaInicio;
    }

    public String getHorafinal() {
        return horaFinal;
    }

    public void setIdCita(long id){
        this.id=id;
    }

    public void setIdSede(Integer sedeId){
        this.sedeId=sedeId;
    }

    public void setIdEmpleado(Long idEmpleado) {
    this.idEmpleado = idEmpleado;
}

    public void setEspecialidadCita(String especialidad){
        this.especialidad=especialidad;
    }

    public void setIdClienteCita(Integer idCliente){
        this.idCliente=idCliente;
    }

/*     public void setNombreSedeCita(String nombreSede){
        this.nombreSede=nombreSede;
    } */

    public void setfechaCita(String fecha){
        this.fecha=fecha;
    }

    public void setHoraInicioCita(String horaInicio){
        this.horaInicio=horaInicio;
    }

    public void setHoraFinalCita(String horaFinal){
        this.horaFinal=horaFinal;
    }
}
