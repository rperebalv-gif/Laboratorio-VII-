package com.universidad.profesores.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Profesor no existe -> 404
    @ExceptionHandler(ProfesorNotFoundException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(ProfesorNotFoundException ex) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("status", 404);
        cuerpo.put("error", "NOT FOUND");
        cuerpo.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuerpo);
    }

    // Falla @Valid (nombre vacío, correo inválido...) -> 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));

        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("status", 400);
        cuerpo.put("error", "BAD REQUEST");
        cuerpo.put("detalles", errores);
        return ResponseEntity.badRequest().body(cuerpo);
    }
}
