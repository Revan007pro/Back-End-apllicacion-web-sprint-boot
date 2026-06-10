package com.citas.usuarios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "Horario_Disponible")
public class Horario_disponible {

    public enum DiaSemana {
        Lunes,
        Martes,
        Miercoles,
        Jueves,
        Viernes
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idHorario;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado") // forma para mapear tablas forraneas
    private Empleados iDEmpleado;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana")
    private DiaSemana diaSemama;

    @Column(name = "hora")
    private LocalTime horaHorario;

    @Column(name = "Estado")
    private Integer estadoHo;

    public LocalTime getHoraHorario() {
        return horaHorario;
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public Empleados getIdEmpleado() {
        return iDEmpleado;
    }

    public DiaSemana getDiaSemana() {
        return diaSemama;
    }

    public Integer getEstadoHo() {
        return estadoHo;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public void setEmpleadoHor(Empleados iDEmpleado) {
        this.iDEmpleado = iDEmpleado;
    }

    public void setDiaSemana(DiaSemana diaSemama) {
        this.diaSemama = diaSemama;
    }

    public void setHoraHorario(LocalTime horaHorario) {
        this.horaHorario = horaHorario;
    }

    public void setEstadoHora(Integer estadoHo) {
        this.estadoHo = estadoHo;
    }
}
