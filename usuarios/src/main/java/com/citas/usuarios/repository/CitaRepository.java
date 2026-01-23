package com.citas.usuarios.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Citas;


public interface CitaRepository extends JpaRepository<Citas, Long>{
    Citas findById(long idCita);
    List<Citas> findByUsuarioNombre(String nombre); 


    // Buscar por el ID de la sede
    List<Citas> findByIdSede(Integer idSede);

    // Buscar por hora de inicio
    List<Citas> findByHoraInicio(LocalTime horaInicio);
    //Citas findByNombre(String nombre);

    // por ahora vacio
}