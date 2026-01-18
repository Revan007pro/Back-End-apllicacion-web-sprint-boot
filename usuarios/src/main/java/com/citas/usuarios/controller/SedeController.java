package com.citas.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.entity.Sede;
import com.citas.usuarios.repository.SedeREpository;





@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/listar/sedes")
public class SedeController {
    @Autowired
    private SedeREpository sedeRepository;

    @GetMapping
    public List<Sede>listarSedes(){
        return sedeRepository.findAll();
    }
    }
    
    

