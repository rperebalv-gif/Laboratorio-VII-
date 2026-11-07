package com.universidad.profesores.controller;

import com.universidad.profesores.model.Profesor;
import com.universidad.profesores.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Profesor> crear(@Valid @RequestBody Profesor profesor) {
        Profesor creado = service.crear(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);   // 201
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> listar(
            @RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(service.listar(especialidad));          // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));              // 200 o 404
    }
}
