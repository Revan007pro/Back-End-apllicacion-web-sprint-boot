package com.citas.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

   // Empresa findByID(long idEmpresa);

}
