package com.universidad.profesores.repository;

import com.universidad.profesores.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfesorRepository {

    // "Base de datos" en memoria
    private final List<Profesor> profesores = new ArrayList<>();
    private long contadorId = 1;

    public Profesor guardar(Profesor profesor) {
        profesor.setId(contadorId++);
        profesores.add(profesor);
        return profesor;
    }

    public List<Profesor> obtenerTodos() {
        return profesores;
    }

    public Profesor buscarPorId(Long id) {
        for (Profesor p : profesores) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
