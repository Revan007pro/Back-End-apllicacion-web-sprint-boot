package com.citas.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Empleados;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
    // Al estar vacío, ya tienes acceso a save(), delete(), findById(), etc.
}