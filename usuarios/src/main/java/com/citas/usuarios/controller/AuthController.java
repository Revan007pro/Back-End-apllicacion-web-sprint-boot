package com.citas.usuarios.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public Map<String, Object> loginPost(@RequestBody LoginRequest request) {
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
            if (userDB.getRoll().equalsIgnoreCase("Administrador")) {
                respuesta.put("urlTarget", "/Administrador");
            }
            else if(userDB.getRoll().equalsIgnoreCase("empleado")){
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
}
