package com.citas.usuarios.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.entity.Horario_disponible;
import com.citas.usuarios.repository.EmpleadosRepository;
import com.citas.usuarios.repository.HorarioRepository;

@RestController
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioRepository dally;

    @Autowired
    private EmpleadosRepository empleadoRepository;

    @GetMapping("listar/horario/disponible")
    public ResponseEntity<Map<String, Object>> listarAll() {

        Map<String, Object> response = new HashMap<>();

        response.put("mensaje", "Horarios encontrados");
        response.put("data", dally.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("listar/horario/disponible/{id}")
    public ResponseEntity<?> listarId(@PathVariable Long id) {
        Optional<Horario_disponible> dallyOptional = dally.findById(id);

        Map<String, Object> respuesta = new HashMap<>();

        if (!dallyOptional.isPresent()) {
            respuesta.put("mensaja", "error no se encuentra el id del horario");
            respuesta.put("codigo", 2);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        respuesta.put("mensaje", "usuario obtenido correctamente");
        respuesta.put("code", 1);
        respuesta.put("datos", dallyOptional);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @GetMapping("encontrar/horario/{idEmpleado}")
    public ResponseEntity<List<Horario_disponible>> horarioEmpl(@PathVariable Long idEmpleado) {
        List<Horario_disponible> horariosDB = dally.buscarHorarioPorUsuarioEmpleado(idEmpleado);

        if (horariosDB.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(horariosDB);
    }
}
