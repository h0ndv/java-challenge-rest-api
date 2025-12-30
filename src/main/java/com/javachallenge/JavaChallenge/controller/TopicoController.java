package com.javachallenge.JavaChallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.javachallenge.JavaChallenge.dto.TopicoDTO;
import com.javachallenge.JavaChallenge.models.Topico;
import com.javachallenge.JavaChallenge.repository.TopicoRepository;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@Validated
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // Endpoint para crear un nuevo topico
    @PostMapping
    public ResponseEntity<?> crearTopico(@Valid @RequestBody TopicoDTO topicoRequest) {
        // Validar que no exista un topico duplicado (mismo titulo y mensaje)
        Topico topicoDuplicado = topicoRepository.findByTituloAndMensaje(
            topicoRequest.getTitulo(), 
            topicoRequest.getMensaje()
        );

        if (topicoDuplicado != null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Ya existe un topico con el mismo titulo y mensaje");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // Crear el nuevo topico
        Topico topico = new Topico();
        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setAutor(topicoRequest.getAutor());
        topico.setCurso(topicoRequest.getCurso());
        topico.setFechaCreacion(new Date());
        topico.setStatus("Activo");

        // Guardar en la base de datos
        Topico topicoGuardado = topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoGuardado);
    }

    // Endpoint para obtener un topico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicoById(@PathVariable int id) {
        // Obtener el topico de la base de datos
        Topico topico = topicoRepository.findTopicoById(id);
        
        if (topico == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Topico no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(topico);
    }

    // Endpoint para obtener todos los topicos
    @GetMapping
    public ResponseEntity<List<Topico>> getTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }

    // Endpoint para actualizar un topico existente por su ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable int id, @Valid @RequestBody TopicoDTO topicoRequest) {
        Topico topico = topicoRepository.findTopicoById(id);
        
        if (topico == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Topico no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        // Actualizar los campos del topico existente
        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setAutor(topicoRequest.getAutor());
        topico.setCurso(topicoRequest.getCurso());

        // Guardar los cambios en la base de datos
        Topico topicoActualizado = topicoRepository.save(topico);
        return ResponseEntity.ok(topicoActualizado);
    }

    // Endpoint para eliminar un topico por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable int id) {
        Topico topico = topicoRepository.findTopicoById(id);
        
        if (topico == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Topico no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        // Eliminar el topico de la base de datos
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}
