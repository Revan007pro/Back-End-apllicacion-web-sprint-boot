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
@CrossOrigin(origins="*")
@PostMapping("/guardar/usuario/version2")
public ResponseEntity <Map<String,Object>>respuestaSave(@RequestBody SaveRequest newSave){
    Map<String,Object>userSave2=new HashMap<>();

    String roll="Cliente"; //cambiar
    String key;
    Integer value;

    String newNombre = newSave.getNewUsuario();
    String newApellidos = newSave.getNewApellidos();
    String newfecha = newSave.getNewfecha_nacimiento();
    String newCorreo = newSave.getNewCorreo();
    String newContrasenia = newSave.getNewContrasenia();
    String newConfirmar = newSave.getNewConfirmar();
    BigInteger newTelefono =newSave.getNewTelefono();

    Usuario newUserDB = newusuarioRepository.findByNombre(newNombre);
    if(newUserDB!=null){
        userSave2.put("code","10");
        userSave2.put("mensaje","El usuario ya existe en el sistema");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(userSave2);
    }

    if(newNombre==null || newNombre.isEmpty()){
        userSave2.put("code","2");
        userSave2.put("mensaje","el nombre no puede ser nulo o vacio");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(userSave2);
    }
    if(newApellidos==null || newApellidos.isEmpty()){
        userSave2.put("code","3");
        userSave2.put("mensaje","los apellidos no pueden ser nulos o vacios");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(userSave2);
    }
    if(newfecha==null || newfecha.isEmpty()){
        userSave2.put("code","4");
        userSave2.put("mensaje","la fecha de nacimiento no puede ser nula");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(userSave2);
    }
     if(newCorreo==null || newCorreo.isEmpty()){
        userSave2.put("code","5");
        userSave2.put("mensaje","el correo no puede ser nulo o vacio");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(userSave2);
    }
    if(newContrasenia==null || newContrasenia.isEmpty()){
        userSave2.put("code","6");
        userSave2.put("mensaje","se necesita crear una contraseña");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userSave2);
    }
     if(newConfirmar==null || newConfirmar.isEmpty()){
        userSave2.put("code","7");
        userSave2.put("mensaje","la verificacion de la contraseña esta vacia");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userSave2);
    }
     if(!newConfirmar.equals(newContrasenia)){
        userSave2.put("code","8");
        userSave2.put("mensaje","las contraseñas no coinciden");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userSave2);
    }
    if(newTelefono==null){
        userSave2.put("code","9");
        userSave2.put("mensaje","se neesita un telefono para avanzar");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userSave2);
    }

    Usuario usuarioSave2=new Usuario();
    usuarioSave2.setNombre(newNombre);
    usuarioSave2.setApellidos(newApellidos);
    usuarioSave2.setCorreo(newCorreo);
    usuarioSave2.setFechaNacmiento(newfecha);
    usuarioSave2.setPassword(passwordEncoder.encode(newContrasenia));
    usuarioSave2.setTelefono(newTelefono);
    usuarioSave2.setNewRoll(roll);
    try{
        newusuarioRepository.save(usuarioSave2);
        userSave2.put("code","1");
        userSave2.put("mensaje","usuario guardado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(userSave2);

    }catch(Exception e){
        userSave2.put("error","error en el servidor no especifico"+e.getMessage());
        return ResponseEntity.badRequest().body(userSave2);
    }
}


}