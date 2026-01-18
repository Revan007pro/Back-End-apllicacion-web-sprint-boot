package com.citas.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Empleados;
import com.citas.usuarios.entity.Usuario;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
    //Usuario findByNombre(String nombre);
    Empleados findByUsuario(Usuario usuario);
    // Al estar vacío, ya tienes acceso a save(), delete(), findById(), etc.
}