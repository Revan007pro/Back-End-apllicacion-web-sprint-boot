package com.citas.usuarios.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citas.usuarios.dto.ClienteCita;
import com.citas.usuarios.dto.EmpleadoCita;
import com.citas.usuarios.entity.Citas;

public interface CitaRepository extends JpaRepository<Citas, Long> {
    Citas findById(long idCita);

    List<Citas> findByUsuarioNombre(String nombre);
    // Buscar por el ID de la sede
    List<Citas> findByIdSede(Integer idSede);

    // Buscar por hora de inicio
    List<Citas> findByHoraInicio(LocalTime horaInicio);
    // Citas findByNombre(String nombre);

    @Query("SELECT new com.citas.usuarios.dto.EmpleadoCita(CAST(e.idEmpleado AS long), u.nombre, CAST(c.idCita AS long)) "
            +
            "FROM Empleados e " +
            "JOIN e.usuario u " +
            "LEFT JOIN Citas c ON e.idEmpleado = c.empleado.idEmpleado")
    List<EmpleadoCita> listarEstadoEmpleados();

    @Query("SELECT c FROM Citas c WHERE c.empleado.idEmpleado = :idEmpleado")
    List<Citas> buscarCitasPorEmpleado(@Param("idEmpleado") Integer idEmpleado);

 @Query("SELECT new com.citas.usuarios.dto.ClienteCita(u.id, u.nombre, c.idCita) " +
           "FROM Citas c JOIN c.usuario u " +
           "WHERE u.id = :idPersona")
    List<ClienteCita> listarCitasClienteDto(@Param("idPersona") Integer idPersona);

    @Query("SELECT c FROM Citas c WHERE c.usuario.id = :idPersona")
    List<Citas> buscarCitasCliente(@Param("idPersona") Integer idPersona);
}