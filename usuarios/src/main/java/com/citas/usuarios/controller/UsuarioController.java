/*  package com.citas.usuarios.controller;
  
  import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.bind.annotation.RestController;
  
  import java.util.HashMap;
  import java.util.Map;
  
  @RestController
  
  @RequestMapping("/api/usuarios")
  public class UsuarioController {
  
  @GetMapping
  public List<Usuario> listar() {
  return UsuarioRepository.findAll();
  }
  }



 */

 package com.citas.usuarios.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.entity.Usuario;
import com.citas.usuarios.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4321")
public class UsuarioController {

    @Autowired
    private UsuarioRepository userRepository;
    
   // @GetMapping
   // public List<Empleados> listar() {
   //     return empleadoRepository.findAll();
   // }


   @GetMapping("consultar/{id_persona}")
   public ResponseEntity<?>obtenerUser(@PathVariable Long id_persona){
    Optional<Usuario> usuario=userRepository.findById(id_persona); //nota falta el repository
    Map<String,Object>respuesta=new HashMap<>();

    if(!usuario.isPresent()){
        respuesta.put("mensaje","El usuario no se encuentra segistrado en el sistema");
        respuesta.put("code",2);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    respuesta.put("mensaje","usuario obtenido correctamente");
    respuesta.put("code",1);
    return ResponseEntity.status(HttpStatus.OK).body(respuesta);

   }
}