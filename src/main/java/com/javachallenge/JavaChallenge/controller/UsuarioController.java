package com.javachallenge.JavaChallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.javachallenge.JavaChallenge.dto.UsuarioDTO;
import com.javachallenge.JavaChallenge.models.Usuario;
import com.javachallenge.JavaChallenge.repository.UsuarioRepository;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Endpoint para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioRequest) {
        
        // Validar que no exista un usuario con el mismo email
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if (usuarioExistente != null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "El email ya esta registrado por otro usuario");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // Crear el nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setPassword(usuarioRequest.getPassword());

        // Guardar en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }

    // Endpoint para obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Endpoint para obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        
        if (usuario == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(usuario);
    }

    // Endpoint para actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioDTO usuarioRequest) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        if (usuario == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        // Validar que el email no este registrado por otro usuario
        Usuario usuarioConEmail = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        
        if (usuarioConEmail != null && usuarioConEmail.getId() != id) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "El email ya esta registrado por otro usuario");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // Actualizar los datos
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setPassword(usuarioRequest.getPassword());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // Endpoint para eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        
        if (usuario == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        usuarioRepository.delete(usuario);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario eliminado");
        return ResponseEntity.ok(respuesta);
    }
}
