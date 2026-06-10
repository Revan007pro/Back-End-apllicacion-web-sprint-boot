package com.citas.usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citas.usuarios.entity.Empleados;
import com.citas.usuarios.entity.Horario_disponible;

public interface HorarioRepository extends JpaRepository<Horario_disponible, Long> {

    List<Horario_disponible> findByIdHorario(Long idHorario);

    Empleados findById(long idEmpleado);

    @Query("SELECT h FROM Horario_disponible h WHERE h.iDEmpleado.idEmpleado = :idEmpleado")
    List<Horario_disponible> buscarHorarioPorUsuarioEmpleado(@Param("idEmpleado") Long idEmpleado);

}
