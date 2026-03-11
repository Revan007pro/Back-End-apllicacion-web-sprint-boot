package com.citas.usuarios.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.dto.LoginRequest;
import com.citas.usuarios.entity.Usuario;
import com.citas.usuarios.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public  Map<String, Object>loginPost(@RequestBody LoginRequest request) {
        Map<String, Object> respuesta = new HashMap<>();

        String nombre = request.getNombre();
        String password = request.getPassword();



        Usuario userDB = usuarioRepository.findByNombre(nombre);


        if (userDB.getNombre().equalsIgnoreCase(nombre) && userDB.getPassword().equalsIgnoreCase(password)) {
            respuesta.put("mensaje", "Bienvenido al sistema");
            Map<String, Object> datosUsuarios = new HashMap<>();
            datosUsuarios.put("roll", userDB.getRoll());
            datosUsuarios.put("nombre", userDB.getNombre());
            datosUsuarios.put("correo", userDB.getCorreo());
            datosUsuarios.put("telefono", userDB.getTelefono());
            datosUsuarios.put("usuario", userDB.getUsuarioAtributo());
            datosUsuarios.put("identificacion", userDB.getId());
            
            if (userDB.getRoll().equalsIgnoreCase("Administrador")) {
                respuesta.put("urlTarget", "/Administrador");
            }
            else if(userDB.getUsuarioAtributo().equalsIgnoreCase("Empleado")){
                respuesta.put("urlTarget", "/empleado");
            }
            else{
                respuesta.put("urlTarget", "/dashboard_usuario"); 
    

            }
            respuesta.put("datos", datosUsuarios);
        } else {
            respuesta.put("mensaje", "Error en el usuario o en el passpord");
        }
        return respuesta;
    }
    @PostMapping("/Login/Status")
    public ResponseEntity <Map<String,Object>> enterUser(@RequestBody LoginRequest request2){
        Map<String,Object>respuesta2 =new HashMap<>();

        String nombre = request2.getNombre();
        String password = request2.getPassword();

        String Key="mensaje";
        Integer Code;
        String Value = "Bienvenido ";

        Usuario userDB = usuarioRepository.findByNombre(nombre);
        if (userDB.getNombre().equalsIgnoreCase(nombre) && userDB.getPassword().equalsIgnoreCase(password)) {
            respuesta2.put("mensaje", "Bienvenido al sistema");
            respuesta2.put("roll", userDB.getRoll());
            respuesta2.put("nombre", userDB.getNombre());
            respuesta2.put("correo", userDB.getCorreo());
            respuesta2.put("telefono", userDB.getTelefono());
            respuesta2.put("usuario", userDB.getUsuarioAtributo());
            respuesta2.put("identificacion", userDB.getId());
            
            if (userDB.getRoll().equalsIgnoreCase("Administrador")) {
                respuesta2.put("urlTarget", "/Administrador");
                respuesta2.put("Code",2);
                respuesta2.put(Key,Value + userDB.getNombre());
                return ResponseEntity.status(HttpStatus.FOUND).body(respuesta2);
            }
            else if(userDB.getUsuarioAtributo().equalsIgnoreCase("Empleado")){
                respuesta2.put("urlTarget", "/empleado");
                respuesta2.put("Code",2);
                respuesta2.put(Key,Value + userDB.getNombre());
                return ResponseEntity.status(HttpStatus.FOUND).body(respuesta2);
            }
            else{
                respuesta2.put("urlTarget", "/dashboard_usuario"); 
                respuesta2.put("Code",2);
                respuesta2.put(Key,Value + userDB.getNombre());
                return ResponseEntity.status(HttpStatus.FOUND).body(respuesta2);
    

            }}
        return ResponseEntity.ok(respuesta2);

    }
}
