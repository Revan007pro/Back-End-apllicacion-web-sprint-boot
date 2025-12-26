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
            datosUsuarios.put("usuario", userDB.getRoll());
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
                respuesta.put("urlTarget", "/Agendar_cita"); /*
                                                            Nota: por ahora cuando se guarde un usuario, y se llame en el login
                                                            el rol no puede ser nulo cuando se lleme en el login, hacer un script
                                                            para que cuando se guarde y el roll es nulo colocar como txt nulo.
                */

            }
            respuesta.put("datos", datosUsuarios);
        } else {
            respuesta.put("mensaje", "Error en el usuario o en el passpord");
        }
        return respuesta;
    }
}
