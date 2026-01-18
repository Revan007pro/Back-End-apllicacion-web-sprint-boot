package com.citas.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Citas;

public interface CitaRepository extends JpaRepository<Citas, Long>{
    Citas findById(long idCita);
    //Citas findByNombre(String nombre);

    // por ahora vacio
}