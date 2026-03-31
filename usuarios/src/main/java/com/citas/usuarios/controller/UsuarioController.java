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
  // return empleadoRepository.findAll();
  // }

  @GetMapping("/consultar/{id}")
  public ResponseEntity<?> obtenerUser(@PathVariable Long id) {
    Optional<Usuario> usuario = userRepository.findById(id); // nota falta el repository
    Map<String, Object> respuesta = new HashMap<>();

    if (!usuario.isPresent()) {
      respuesta.put("mensaje", "El usuario no se encuentra segistrado en el sistema");
      respuesta.put("code", 2);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    respuesta.put("mensaje", "usuario obtenido correctamente");
    respuesta.put("code", 1);
    respuesta.put("datos", usuario);
    return ResponseEntity.status(HttpStatus.OK).body(respuesta);

  }

  @GetMapping("/encontrar/inactivados")
  public List<Usuario> userInactivados() {
    return userRepository.findByEstadoUser(0); /*
                                                * retorna los usuarios que esta
                                                * desactivados, se hace desactivando
                                                * los argumentos en el metodo
                                                */
  }

  @PostMapping("activar/usuario/{id}")
  public ResponseEntity<Map<String, Object>> setearUsuario(@PathVariable Long id) {
    Map<String, Object> respuesta = new HashMap<>();
    Optional<Usuario> usuarOptional = userRepository.findById(id);

    if (usuarOptional.isPresent()) {
      Usuario usuarioDB = usuarOptional.get();
      usuarioDB.setEstadoUser(1);
      userRepository.save(usuarioDB);
      respuesta.put("codigo", 1);
      respuesta.put("mensaje", "usuario habilitado");
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(respuesta);
    }

    return ResponseEntity.ok(respuesta);

  }

}
