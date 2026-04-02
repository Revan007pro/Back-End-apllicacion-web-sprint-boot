package com.citas.usuarios.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cita")
public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private long idCita;

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Usuario usuario; // forma para traer otros id de otras tablas

    @OneToMany(mappedBy = "citaFactu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura> facturas;
    /*
     * CascadeType.ALL, Cualquier cosa que le pase al padre, hazle lo mismo al hijo
     * automáticamente
     * guardar,actualizar, borrar,
     * orphanRemoval "Limpieza de Huérfanos "
     * Si en tu código quitas una factura de la lista de facturas de la cita, Java
     * dice: "Ah, esta factura ya no
     * tiene padre (cita), entonces ya no sirve para nada". Y la borra
     * automáticamente de
     * la base de datos.
     */

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado") // forma para mapear tablas forraneas
    private Empleados empleado;

    @Column(name = "id_sede")
    private Integer idSede;

    @Column(name = "Estado")
    private Integer estado;

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    @Column(name = "fecha")
    private LocalDate citaFecha;

    @Column(name = "hora_cita")
    private LocalTime horaInicio;

    public Integer getEstadoCita() {
        return estado;
    }

    public long getIdCita() {
        return idCita;

    }

    public Usuario getUsuarioCita() {
        return usuario;
    }

    public Empleados getEmpleadosCita() {
        return empleado;
    }

    public LocalDate getFechaCita() {
        return citaFecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setEStadoCita(Integer estado) {
        this.estado = estado;
    }

    public void setIdCita(long idCita) {
        this.idCita = idCita;
    }

    public void setUsuarioCita(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setEmpleadoCita(Empleados empleado) {
        this.empleado = empleado;
    }

    public void setFechaCita(LocalDate citaFecha) {
        this.citaFecha = citaFecha;
    }

    public void sethoraInicioCita(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

}
