package com.citas.usuarios.dto;

import java.time.LocalTime;

public class HorarioRequest {
    private Long idHorario;
    private Long idEmpleado;
    private String diaSemana;
    private LocalTime horaHorario;
    private Integer estadoHorario;

    public HorarioRequest(Long idHorario, Long idEmpleado, String diaSemana, LocalTime horaHorario,
            Integer estadoHorario) {
        this.idHorario = idHorario;
        this.idEmpleado = idEmpleado;
        this.diaSemana = diaSemana;
        this.horaHorario = horaHorario;
        this.estadoHorario = estadoHorario;
    }

    public HorarioRequest() {
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraHorario() {
        return horaHorario;
    }

    public Integer getEstadoHorario() {
        return estadoHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setHoraHorario(LocalTime horaHorario) {
        this.horaHorario = horaHorario;
    }

    public void setEstadoHorario(Integer estadoHorario) {
        this.estadoHorario = estadoHorario;
    }

}
