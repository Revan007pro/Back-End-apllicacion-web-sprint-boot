package com.citas.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Tipo_Servicio;

public interface TServicioRepository  extends JpaRepository<Tipo_Servicio, Long>{
        
}
