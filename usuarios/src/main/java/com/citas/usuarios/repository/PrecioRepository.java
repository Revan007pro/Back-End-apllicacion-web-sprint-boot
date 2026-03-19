package com.citas.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Precios;

public interface PrecioRepository extends JpaRepository<Precios, Long>{
    //hacer una querry con la factura y los precios



}
