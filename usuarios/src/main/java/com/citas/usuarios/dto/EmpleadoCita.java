package com.citas.usuarios.dto;

public class EmpleadoCita {

    private Long idEmpleado;
    private String nombre;
    private Long idCita;

    public EmpleadoCita(Long idEmpleado, String nombre, Long idCita) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.idCita = idCita;
    }

    public EmpleadoCita() {}

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }
}
