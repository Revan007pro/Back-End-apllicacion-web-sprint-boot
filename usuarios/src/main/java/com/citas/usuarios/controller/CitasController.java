package com.citas.usuarios.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.dto.CitasRequest;
import com.citas.usuarios.entity.Citas;
import com.citas.usuarios.entity.Empleados;
import com.citas.usuarios.entity.Usuario;
import com.citas.usuarios.repository.CitaRepository;
import com.citas.usuarios.repository.EmpleadosRepository;
import com.citas.usuarios.repository.SedeREpository;
import com.citas.usuarios.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins="*") //cualquiera en el front puede acceder a esta api
public class CitasController {
    @Autowired
    private CitaRepository nuevaCita;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpleadosRepository empleadoRepository;
    @Autowired
    private SedeREpository sedeRepository;


    @PostMapping("/guardar/cita")
    public Map<String,Object>SaveCita(@RequestBody CitasRequest newsCita){
        Map<String,Object>Respuesta = new HashMap<>();

        long citaId=newsCita.getId();
        Long idEmpleado = newsCita.getIdEmpleado();
        Integer idSede = newsCita.getSedeId();
        //String nombreSede = newsCita.getNombreSede();
        
        String nombreCita=newsCita.getEspecialidad();
        String nombreEspec = newsCita.getNombreEspecialista();
        Integer idClien=newsCita.getIdCliente();
        
        String fechaCita=newsCita.getFecha();
        String horaInicio=newsCita.getHorainicio();
        String horaFinal=newsCita.getHorafinal();

        Citas citasBS = nuevaCita.findById(citaId);
        Empleados empleadoDB = empleadoRepository.findById(idEmpleado).orElse(null);
        Usuario usuarioDB = usuarioRepository.findById(idClien);

        if (citasBS !=null) {
            Respuesta.put("Error", "la cita ya existe");
            return Respuesta;
        }
        if (empleadoDB == null) {
        Respuesta.put("status", "error");
        Respuesta.put("mensaje", "No se encontró el registro de empleado para: " + nombreEspec);
        return Respuesta; 
    }
        Citas citaNueva=new Citas();

        citaNueva.setIdSede(idSede);
        //citaNueva.setNombreSede(nombreSede);
        citaNueva.setUsuarioCita(usuarioDB);
        citaNueva.setEmpleadoCita(empleadoDB); //nueva logica para buscar empleado
        citaNueva.setFechaCita(LocalDate.parse(fechaCita));
        citaNueva.sethoraInicioCita(LocalTime.parse(horaInicio));
       

        try{
            nuevaCita.save(citaNueva);
            Respuesta.put("status", "ok");
            Respuesta.put("mensaje", "Cita guardada correctamente");
            return Respuesta;
        }
        catch(Exception e){
            Respuesta.put("status", "error");
            Respuesta.put("mensaje", "Error al guardar la cita");
            return Respuesta;
        }





    }
    @GetMapping("citas/mostrar")
    public List<Citas>listarCita(){
        return nuevaCita.findAll(); // peligro devuelve todo lo de mi base de datos 
    }
    
    @GetMapping("citas/mostrar/cliente/nombre")
    public List<Citas>mortrarCliente(@RequestParam String nombre){
        return nuevaCita.findByUsuarioNombre(nombre);
    }


    @GetMapping("citas/mostrar/cliente/id")
    public Citas mostrarClienteId(@RequestParam long idCita) {
        return nuevaCita.findById(idCita); 
}

    @GetMapping("citas/mostrar/hora")
    public List<Citas>mortrarHora(@RequestParam String hora){
        LocalTime horaLocal=LocalTime.parse(hora);
        return nuevaCita.findByHoraInicio(horaLocal);
    }

     @GetMapping("citas/mostrar/sede")
    public List<Citas>mortrarSedeCita(@RequestParam Integer idSede){
        return nuevaCita.findByIdSede(idSede);
    }


   @DeleteMapping("/citas/borrar") 
public Map<String, Object> borrarCita(@RequestParam long idCita) {
    Map<String, Object> deleteCita = new HashMap<>();

    try{
        if(nuevaCita.existsById(idCita)){
            nuevaCita.deleteById(idCita); //borra los datos por id
            deleteCita.put("mensaje", "Cita eliminada correctamente");
            deleteCita.put("status", true);
        return deleteCita;
        }
    }catch(Exception e){
            deleteCita.put("status", "error");
            deleteCita.put("mensaje", "Error al borrar cita");
            return deleteCita;
        }


    
    return deleteCita;

    

}}
