package com.citas.usuarios.controller;

import java.math.BigInteger;
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
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.dto.LoginRequest;
import com.citas.usuarios.dto.UsuarioRequest;
import com.citas.usuarios.entity.Usuario;
import com.citas.usuarios.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    public UsuarioRepository usuarioRepository;

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
            datosUsuarios.put("usuario", userDB.getUsuarioAtributo());
            datosUsuarios.put("identificacion", userDB.getId());

            if (userDB.getRoll().equalsIgnoreCase("Administrador")) {
                respuesta.put("urlTarget", "/Administrador");
            } else if (userDB.getUsuarioAtributo().equalsIgnoreCase("Empleado")) {
                respuesta.put("urlTarget", "/empleado");
            } else {
                respuesta.put("urlTarget", "/dashboard_usuario");

            }
            respuesta.put("datos", datosUsuarios);
        } else {
            respuesta.put("mensaje", "Error en el usuario o en el passpord");
        }
        return respuesta;
    }

    @PostMapping("/Login/Status")
    public ResponseEntity<Map<String, Object>> enterUser(@RequestBody LoginRequest request2) {
        Map<String, Object> respuesta2 = new HashMap<>();

        String nombre = request2.getNombre();
        String password = request2.getPassword();

        String Key = "mensaje";
        String Value = "Bienvenido ";

        Usuario userDB = usuarioRepository.findByNombre(nombre);
        if ((nombre == null || nombre.isEmpty()) && (password == null || password.isEmpty())) {
            respuesta2.put("mensaje", "Debe ingresar datos para poder ingresar");
            respuesta2.put("Code", 2);
            return ResponseEntity.badRequest().body(respuesta2);
        }

        if (userDB == null) {
            respuesta2.put("mensaje", "Usuario no encontrado");
            respuesta2.put("Code", 6);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta2);
        }

        if (nombre == null || nombre.isEmpty()) {
            respuesta2.put("mensaje", "Debe ingresar un nombre válido");
            respuesta2.put("Code", 4);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(respuesta2);
        }

        if (password == null || password.isEmpty()) {
            respuesta2.put("mensaje", "Debe ingresar una contraseña válida");
            respuesta2.put("Code", 3);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta2);
        }
        if (userDB.getEstadoUser() == 0) {
            respuesta2.put("mensaje", "Usuario inabilitado por favor contactese con el administrador");
            respuesta2.put("Code", 7);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta2);
        }

        if (userDB.getNombre().equalsIgnoreCase(nombre) && !userDB.getPassword().equals(password)) {
            respuesta2.put("mensaje", "contraseña no valida");
            respuesta2.put("Code", 5);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(respuesta2);
        }
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
                respuesta2.put("Code", 1);
                respuesta2.put(Key, Value + userDB.getNombre());
                return ResponseEntity.status(HttpStatus.FOUND).body(respuesta2);
            } else if (userDB.getRoll().equalsIgnoreCase("Empleado")) {
                respuesta2.put("urlTarget", "/empleado");
                respuesta2.put("Code", 1);
                respuesta2.put(Key, Value + userDB.getNombre());
                return ResponseEntity.status(HttpStatus.FOUND).body(respuesta2);
            } else {
                respuesta2.put("urlTarget", "/dashboard_usuario");
                respuesta2.put("Code", 1);
                respuesta2.put(Key, Value + userDB.getNombre());
                return ResponseEntity.status(HttpStatus.FOUND).body(respuesta2);

            }
        }
        return ResponseEntity.ok(respuesta2);

    }

    @GetMapping("/listar/usuarios")
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Usuario> usuarios = usuarioRepository.findAll();
        // return usuarioRepository.findAll();
        try {
            if (usuarios.isEmpty()) {
                respuesta.put("code", 2);
                respuesta.put("datos", usuarios);
                respuesta.put("mensaje", "probablemente no hay registros en la base de datos");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }
            respuesta.put("mensaje", "exito en enviar todos los usuarios del sistema");
            respuesta.put("code", 1);
            respuesta.put("datos", usuarios);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);

        } catch (Exception err) {
            respuesta.put("mensaje", "error interno");
            respuesta.put("code", 3);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

    }

    @PutMapping("/actualizar/datos")
    public ResponseEntity<Map<String, Object>> acutalizarDatos(@RequestBody UsuarioRequest userData) {
        Map<String, Object> datos = new HashMap<>();

        Long id_persona = userData.getIdPersona();

        String newNombre = userData.getNombreUser();
        String newApellido = userData.getApellidosUser();
        String newFechaNacimiento = userData.getFechaUser();
        String newCorreo = userData.getCorreoUser();
        String newPassword = userData.getContraseniaUser();
        String newRoll = userData.getRollUser();
        BigInteger newTelefono = userData.getNewTelefono();

        Usuario userDB = usuarioRepository.findById(id_persona).orElse(null);
        if (newPassword == null || newPassword.isEmpty()) {
            datos.put("mensaje", "la contraseña no puede ser nula");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(datos);
        }

        // Usuario nuevosDatos=new Usuario();
        userDB.setNombre(newNombre);
        userDB.setApellidos(newApellido);
        userDB.setFechaNacmiento(newFechaNacimiento);
        userDB.setCorreo(newCorreo);
        userDB.setNewRoll(newRoll);
        userDB.setPassword(newPassword);
        userDB.setTelefono(newTelefono);

        try {
            usuarioRepository.save(userDB);
            datos.put("mensaje", "Datos actualizados correctamente");

        } catch (Exception e) {
            datos.put("mensaje", "error de origen interno");
        }

        return ResponseEntity.ok(datos);

    }

    @PostMapping("/inactivar/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> inactivarUser(@PathVariable Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Usuario> usuarOptional = usuarioRepository.findById(id);

        if (usuarOptional.isPresent()) {
            Usuario usuario = usuarOptional.get();

            usuario.setEstadoUser(0);
            usuarioRepository.save(usuario);
            respuesta.put("codigo", 1);
            respuesta.put("mensaje", "usuario inabilitado");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(respuesta);
        } else {
            // usuario.setEstadoUser(1);
            respuesta.put("codigo", 2);
            respuesta.put("mensaje", "error en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

        // return ResponseEntity.ok(respuesta);
    }

}
