package com.citas.usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Herramienta;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    // El nombre del método debe terminar exactamente como se llama el atributo en
    // la Entity
    List<Herramienta> findByDisponibleTool(Boolean disponible);
}
