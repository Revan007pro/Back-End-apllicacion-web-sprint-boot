package com.citas.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Sede;

public interface SedeREpository extends JpaRepository<Sede, Long> {
    //vacio por ahora
}
