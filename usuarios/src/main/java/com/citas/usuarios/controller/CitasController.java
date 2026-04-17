package com.citas.usuarios.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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
// @CrossOrigin(origins = "*") // cualquiera en el front puede acceder a esta
// api
@CrossOrigin(origins = "*")
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

    @GetMapping("mostrar/citas/estado/activo")
    public List<Citas> mostrarCitas(@RequestParam Integer estadoCita) {
        return nuevaCita.findByEstado(1);
    }

    /*
     * @GetMapping("mostrar/citas/estado")
     * public List<Citas> mostrarTodasCitas() {
     * return nuevaCita.findAll();
     * }
     */

    @GetMapping("mostrar/citas/estado")
    public ResponseEntity<Map<String, Object>> mostrarTablaita() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Citas> listaCitas = nuevaCita.findAll();

        try {
            if (listaCitas.isEmpty()) {
                respuesta.put("code", 2);
                respuesta.put("mensaje", "probablemente no hay registros en la base de datos");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }
            respuesta.put("code", 1);
            respuesta.put("data", listaCitas);
            respuesta.put("mensaje", "las citas son");
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);

        } catch (Exception err) {
            respuesta.put("mensaje", "error interno");
            respuesta.put("code", 3);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("cancelar/citas/{idCita}")
    public ResponseEntity<Map<String, Object>> cancelarCita(@PathVariable Long idCita) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Citas> citasDB = nuevaCita.findById(idCita);
        try {
            if (!citasDB.isPresent()) {
                respuesta.put("code", 2);
                respuesta.put("mensaje", "probablemente no hay registros en la base de datos");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }
            Citas cita = citasDB.get();
            cita.setEStadoCita(3);
            respuesta.put("codigo", 1);
            nuevaCita.save(cita);
            respuesta.put("data", cita);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            respuesta.put("codigo", 3);
            respuesta.put("mensaje", "error en el servidor");
            return ResponseEntity.badRequest().body(respuesta);
        }

    }

    // @GetMapping("mostrar/citas/estado")
    // public ResponseEntity<List<Creturn ResponseEntity.status(HttpStatus.OK)itas>>
    // mostrarCitas(@RequestParam Integer
    // estadoCita) {
    // return ResponseEntity
    // .status(HttpStatus.OK)
    // .body(nuevaCita.findByEstado(estadoCita));
    // }
    /*
     * @GetMapping("citas/usuario/{idPersona}")
     * public List<Citas> listarCitasPorUsuario(@PathVariable Long idPersona) {
     * return nuevaCita.findByUsuarioIdPersona(idPersona);
     * }
     */

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

    /*
     * @CrossOrigin(origins="*")
     * 
     * @PutMapping("/reprogramar/cita/cliente/{idCita}")
     * public ResponseEntity<Map<String, String>> reprogramarCitas(@PathVariable
     * Integer idCita) {
     * 
     * Map<String, String> response = new HashMap<>();
     * response.put("mensaje", "Mando este mensaje desde el backend"); solo para
     * verificar
     * 
     * return ResponseEntity.ok(response);
     * }
     */

    @CrossOrigin(origins = "*")
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

    @PutMapping("/citas/borrar/{idCita}")
    public Map<String, Object> borrarCita(@PathVariable long idCita) {
        Map<String, Object> deleteCita = new HashMap<>();

        try {
            if (!nuevaCita.existsById(idCita)) {
                deleteCita.put("mensaje", "error la cita no ha sido encontrada");
                deleteCita.put("codigo", 2);
                deleteCita.put("status", false);
                return deleteCita;
            }
            if (nuevaCita.existsById(idCita)) {
                nuevaCita.deleteById(idCita); // borra los datos por id
                deleteCita.put("mensaje", "Cita eliminada correctamente");
                deleteCita.put("codigo", 1);
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

    @PutMapping("/citas/borrar/vesion2/{idCita}")
    public ResponseEntity<Map<String, Object>> borrarCitaV2(@RequestParam long idCita) {
        Map<String, Object> deleteCita2 = new HashMap<>();

        try {
            if (!nuevaCita.existsById(idCita)) {
                deleteCita2.put("mensaje", "error la cita no ha sido encontrada");
                deleteCita2.put("codigo", 2);
                deleteCita2.put("status", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deleteCita2);

            }
            if (nuevaCita.existsById(idCita)) {
                nuevaCita.deleteById(idCita); // borra los datos por id
                deleteCita2.put("mensaje", "Cita eliminada correctamente");
                deleteCita2.put("codigo", 1);
                deleteCita2.put("status", true);
                return ResponseEntity.status(HttpStatus.OK).body(deleteCita2);

            }
        } catch (Exception e) {
            deleteCita2.put("status", "error");
            deleteCita2.put("mensaje", "Error al borrar cita");
            deleteCita2.put("codigo", 3);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(deleteCita2);

        }

        return ResponseEntity.ok(deleteCita2);

    }

    // @CrossOrigin(origins="*")
    // @DeleteMapping("/borrar/cita/version2/{idCita}")

}
