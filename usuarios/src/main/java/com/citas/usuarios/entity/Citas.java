package com.citas.usuarios.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Cita")
public class Citas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_cita")
    private long idCita;

   @ManyToOne 
@JoinColumn(name="id_persona", referencedColumnName ="id_persona")
private Usuario usuario;

@ManyToOne 
@JoinColumn(name="id_empleado", referencedColumnName="id_empleado")
private Empleados empleado;


    @Column(name="id_sede")
private Integer idSede;



    public void setIdSede(Integer idSede) {
    this.idSede = idSede;
}


    @Column(name="fecha")
    private LocalDate citaFecha;


    @Column(name="hora_cita")
    private LocalTime horaInicio;



    public long getIdCita(){
        return idCita;

    }


    public Usuario getUsuarioCita(){
        return usuario;
    }

    public Empleados getEmpleadosCita(){
        return empleado;
    }

    public LocalDate getFechaCita(){
        return citaFecha;
    }

    public LocalTime getHoraInicio(){
        return horaInicio;
    }


    public void setIdCita(long idCita){
        this.idCita=idCita;
    }


    public void setUsuarioCita(Usuario usuario){
        this.usuario=usuario;
    }

    public void setEmpleadoCita(Empleados empleado){
        this.empleado=empleado;
    }

    public void setFechaCita(LocalDate citaFecha){
        this.citaFecha=citaFecha;
    }

    public void sethoraInicioCita(LocalTime horaInicio){
        this.horaInicio=horaInicio;
    }



}
