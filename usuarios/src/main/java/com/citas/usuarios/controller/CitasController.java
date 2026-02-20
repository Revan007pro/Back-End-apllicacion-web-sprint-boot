package com.citas.usuarios.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.dto.CitasReprogramar;
import com.citas.usuarios.dto.CitasRequest;
import com.citas.usuarios.dto.EmpleadoCita;
import com.citas.usuarios.entity.Citas;
import com.citas.usuarios.entity.Empleados;
import com.citas.usuarios.entity.Usuario;
import com.citas.usuarios.repository.CitaRepository;
import com.citas.usuarios.repository.EmpleadosRepository;
import com.citas.usuarios.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*") // cualquiera en el front puede acceder a esta api
public class CitasController {
    @Autowired
    private CitaRepository nuevaCita;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpleadosRepository empleadoRepository;
 
    @PostMapping("/guardar/cita")
    public Map<String, Object> SaveCita(@RequestBody CitasRequest newsCita) {
        Map<String, Object> Respuesta = new HashMap<>();

        // long citaId=newsCita.getId();
        Long idEmpleado = newsCita.getIdEmpleado();
        Integer idCliente = newsCita.getIdCliente();
        String fechaCita = newsCita.getFecha();
        String horaInicio = newsCita.getHorainicio();

        Empleados empleadoDB = empleadoRepository.findById(idEmpleado).orElse(null);
        Usuario usuarioDB = usuarioRepository.findById(idCliente);

        if (newsCita.getFecha() == null || newsCita.getHorainicio() == null) {
            Respuesta.put("status", "error");
            Respuesta.put("mensaje", "La fecha o la hora no pueden estar vacías.");
            return Respuesta;
        }

        Citas citaNueva = new Citas();
        citaNueva.setIdSede(newsCita.getSedeId());
        citaNueva.setUsuarioCita(usuarioDB);
        citaNueva.setEmpleadoCita(empleadoDB);

        citaNueva.setFechaCita(LocalDate.parse(fechaCita));
        citaNueva.sethoraInicioCita(LocalTime.parse(horaInicio));

        try {
            nuevaCita.save(citaNueva);
            Respuesta.put("status", "ok");
            Respuesta.put("mensaje", "Cita guardada correctamente");
            return Respuesta;
        } catch (Exception e) {
            Respuesta.put("status", "error");
            Respuesta.put("mensaje", "Error al guardar la cita");
            return Respuesta;
        }

    }

    @GetMapping("citas/mostrar")
    public List<Citas> listarCita() {
        return nuevaCita.findAll(); // peligro devuelve todo lo de mi base de datos
    }

    @GetMapping("citas/mostrar/cliente/nombre")
    public List<Citas> mortrarCliente(@RequestParam String nombre) {
        return nuevaCita.findByUsuarioNombre(nombre);
    }

    /*
     * @GetMapping("citas/usuario/{idPersona}")
     * public List<Citas> listarCitasPorUsuario(@PathVariable Long idPersona) {
     * return nuevaCita.findByUsuarioIdPersona(idPersona);
     * }
     */

    @CrossOrigin(origins = "http://localhost:4321")
    @GetMapping("/estado-empleados")
    public List<EmpleadoCita> obtenerEstado() { // retorna todos los empleados quienes tienen cita
        return nuevaCita.listarEstadoEmpleados();
    }

    /*
     * @GetMapping("/citas/mostrar/{id}")
     * public ResponseEntity<Citas> obtenerPorId(@PathVariable Long id) {
     * 
     * return nuevaCita.findById(id)
     * .map(ResponseEntity::ok)
     * .orElse(ResponseEntity.notFound().build());
     * }
     */

    @GetMapping("/citas/mostrar/empleado/{idEmpleado}")
    public ResponseEntity<List<Citas>> listarCitasEmpleado(@PathVariable Integer idEmpleado) {

        List<Citas> citas = nuevaCita.buscarCitasPorEmpleado(idEmpleado);

        return ResponseEntity.ok(citas);
    }
    @CrossOrigin(origins = "http://localhost:4321")
    @GetMapping("/citas/mostrar/cliente/{idPersona}")
    public ResponseEntity<List<Citas>> listarCitasCliente(@PathVariable Integer idPersona) {
        List<Citas> citasCliente = nuevaCita.buscarCitasCliente(idPersona);
        return ResponseEntity.ok(citasCliente);
    } 

/*  @CrossOrigin(origins="*")
@PutMapping("/reprogramar/cita/cliente/{idCita}")
public ResponseEntity<Map<String, String>> reprogramarCitas(@PathVariable Integer idCita) {

    Map<String, String> response = new HashMap<>();
    response.put("mensaje", "Mando este mensaje desde el backend"); solo para verificar

    return ResponseEntity.ok(response);
} */

@CrossOrigin(origins="*")
@PutMapping("/reprogramar/cita/cliente/{idCita}")
public Map<String, Object> reprogramarCitas(@PathVariable Long idCita, @RequestBody CitasReprogramar reproCita) {

    Map<String, Object> response = new HashMap<>();

    if (reproCita == null) {
        response.put("status", "error");
        response.put("mensaje", "no se recibieron datos.");
        return response;
    }

    String newFecha = reproCita.getFecha();
    String horaR = reproCita.getHorainicio();

    if (newFecha == null || horaR == null) {
        response.put("status", "error");
        response.put("mensaje", "La fecha o la hora no pueden estar vacías.");
        return response;
    }

    return nuevaCita.findById(idCita).map(citaExistente -> {
        try {
            citaExistente.setFechaCita(LocalDate.parse(newFecha));
            citaExistente.sethoraInicioCita(LocalTime.parse(horaR));

            nuevaCita.save(citaExistente);

            response.put("status", "ok");
            response.put("mensaje", "Cita reprogramada correctamente");
            return response;

        } catch (Exception e) {
            response.put("status", "error");
            response.put("mensaje", "Error al parsear fecha/hora: " + e.getMessage());
            return response;
        }

    }).orElseGet(() -> {
        response.put("status", "error");
        response.put("mensaje", "La cita con ID " + idCita + " no existe.");
        return response;
    });
}

    @GetMapping("citas/mostrar/hora")
    public List<Citas> mortrarHora(@RequestParam String hora) {
        LocalTime horaLocal = LocalTime.parse(hora);
        return nuevaCita.findByHoraInicio(horaLocal);
    }

    @GetMapping("citas/mostrar/sede")
    public List<Citas> mortrarSedeCita(@RequestParam Integer idSede) {
        return nuevaCita.findByIdSede(idSede);
    }

    @PutMapping("/citas/borrar")
    public Map<String, Object> borrarCita(@RequestParam long idCita) {
        Map<String, Object> deleteCita = new HashMap<>();

        try {
            if (nuevaCita.existsById(idCita)) {
                nuevaCita.deleteById(idCita); // borra los datos por id
                deleteCita.put("mensaje", "Cita eliminada correctamente");
                deleteCita.put("status", true);
                return deleteCita;
            }
        } catch (Exception e) {
            deleteCita.put("status", "error");
            deleteCita.put("mensaje", "Error al borrar cita");
            return deleteCita;
        }

        return deleteCita;

    }
}
