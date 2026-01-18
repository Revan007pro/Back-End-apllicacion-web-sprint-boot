package com.citas.usuarios.controller;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.dto.SaveRequest;
import com.citas.usuarios.entity.Usuario;
import com.citas.usuarios.repository.UsuarioRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@CrossOrigin(origins = "*")
public class GuardarUsuario {

    @Autowired
    private UsuarioRepository newusuarioRepository;

  /*   @Autowired
    private PasswordEncoder passwordEncoder; */

    @PostMapping("/guardar/usuario")
public Map<String, Object> savePost(@RequestBody SaveRequest newRequest) {
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

    if (newuserDB != null) {
        respuesta.put("status", "error");
        respuesta.put("mensaje", "El usuario ya existe");
        return respuesta;
    }

    if (!newContrasenia.equals(newConfirmar)) {
        respuesta.put("status", "error");
        respuesta.put("mensaje", "Las contraseñas no coinciden");
        return respuesta;
    }

    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setNombre(newNombre); 
    nuevoUsuario.setApellidos(newApellidos);
    nuevoUsuario.setCorreo(newCorreo);
    nuevoUsuario.setPassword(newContrasenia); 
    nuevoUsuario.setFechaNacmiento(newfecha);
    //nuevoUsuario.setNewConfirmar(newConfirmar);
    nuevoUsuario.setTelefono(newTelefono); 
    nuevoUsuario.setNewRoll(roll); // coloca para empezar a cliente todos los nuevos usuarios

/*     String passwordHasheada = passwordEncoder.encode(newContrasenia);
    nuevoUsuario.setPassword(passwordHasheada); */

    

    try {
        newusuarioRepository.save(nuevoUsuario);
        respuesta.put("mensaje", "Usuario guardado correctamente");
    } catch (Exception e) {
        respuesta.put("mensaje", "Error al guardar: " + e.getMessage());
    }

    return respuesta;
}
}