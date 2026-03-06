package com.citas.usuarios.controller;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.dto.SaveRequest;
import com.citas.usuarios.entity.Usuario;
import com.citas.usuarios.repository.UsuarioRepository;

//import org.springframework.security.crypto.password.PasswordEncoder;



@ResponseStatus(HttpStatus.CREATED)
@RestController
@CrossOrigin(origins = "*")
public class GuardarUsuario {

    @Autowired
    public UsuarioRepository newusuarioRepository;

   

   @Autowired
    public PasswordEncoder passwordEncoder;

    
    @PostMapping("/guardar/usuario")
    @CrossOrigin(origins="*")
public ResponseEntity <Map<String, Object>> savePost(@RequestBody SaveRequest newRequest) {
    Map<String, Object> respuesta = new HashMap<>();

    String roll="Cliente";

    String newNombre = newRequest.getNewUsuario();
// String newRoll=newRequest.getNewRoll();
    String newApellidos = newRequest.getNewApellidos();
    String newfecha = newRequest.getNewfecha_nacimiento();
    String newCorreo = newRequest.getNewCorreo();
    String newContrasenia = newRequest.getNewContrasenia();
    String newConfirmar = newRequest.getNewConfirmar();
    BigInteger newTelefono = newRequest.getNewTelefono();

    Usuario newuserDB = newusuarioRepository.findByNombre(newNombre);

    if (newuserDB != null ) {
        respuesta.put("mensaje", "El usuario ya existe");
        return ResponseEntity.badRequest().body(respuesta);
    }

    if (!newContrasenia.equals(newConfirmar)) {
        respuesta.put("status", "error");
        respuesta.put("mensaje", "Las contraseñas no coinciden");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setNombre(newNombre); 
    nuevoUsuario.setApellidos(newApellidos);
    nuevoUsuario.setCorreo(newCorreo);
    nuevoUsuario.setPassword(passwordEncoder.encode(newContrasenia)); //encriptar el string que manda el usuario
    nuevoUsuario.setFechaNacmiento(newfecha);
    //nuevoUsuario.setNewConfirmar(newConfirmar);
    nuevoUsuario.setTelefono(newTelefono); 
    nuevoUsuario.setNewRoll(roll); // coloca para empezar a cliente todos los nuevos usuarios


    try {
        newusuarioRepository.save(nuevoUsuario);
        respuesta.put("mensaje", "Usuario guardado correctamente");
    } catch (Exception e) {
        respuesta.put("mensaje", "Error al guardar: " + e.getMessage());
    }

    return ResponseEntity.ok(respuesta);
}
}