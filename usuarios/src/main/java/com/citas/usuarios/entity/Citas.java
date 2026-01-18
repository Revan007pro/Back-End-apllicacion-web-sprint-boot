package com.citas.usuarios.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Cita")
public class Citas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_cita")
    private long idCita;

    @OneToOne
    @JoinColumn(name="id_persona",referencedColumnName ="id_persona")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name="id_empleado", referencedColumnName="id_empleado")
    private Empleados empleado;


    @Column(name="id_sede")
private Integer idSede;

/* @Column(name="nombre_sede")
private String nombreSede; */

    public void setIdSede(Integer idSede) {
    this.idSede = idSede;
}

/* public void setNombreSede(String nombreSede) {
    this.nombreSede = nombreSede;
}
 */

    @Column(name="fecha")
    private LocalDate citaFecha;


    @Column(name="hora_inicio")
    private LocalTime horaInicio;


    @Column(name="hora_final")
    private LocalTime horaFinal;


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

    public LocalTime getHoraFinal(){
        return horaFinal;
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

    public void sethoraFinalCita(LocalTime horaFinal){
        this.horaFinal=horaFinal;
    }

}
