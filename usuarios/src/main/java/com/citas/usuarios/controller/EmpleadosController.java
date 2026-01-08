package com.citas.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.entity.Empleados;
import com.citas.usuarios.repository.EmpleadosRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4321")
@RequestMapping("/usuario/empleados")
public class EmpleadosController {

    @Autowired
    private EmpleadosRepository empleadoRepository;
    
    @GetMapping
    public List<Empleados> listar() {
        return empleadoRepository.findAll();
    }
}