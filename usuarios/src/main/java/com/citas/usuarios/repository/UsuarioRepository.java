package com.citas.usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citas.usuarios.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
    Usuario findById(long  id_persona);
    List<Usuario>findByEstadoUser(Integer estadoUser);
}
