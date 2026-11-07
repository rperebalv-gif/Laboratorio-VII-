package com.universidad.profesores.service;

import com.universidad.profesores.exception.ProfesorNotFoundException;
import com.universidad.profesores.model.Profesor;
import com.universidad.profesores.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public Profesor crear(Profesor profesor) {
        return repository.guardar(profesor);
    }

    // Si especialidad es null devuelve todos; si no, filtra
    public List<Profesor> listar(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return repository.obtenerTodos();
        }
        List<Profesor> filtrados = new ArrayList<>();
        for (Profesor p : repository.obtenerTodos()) {
            if (p.getEspecialidad() != null
                    && p.getEspecialidad().equalsIgnoreCase(especialidad)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public Profesor obtenerPorId(Long id) {
        Profesor profesor = repository.buscarPorId(id);
        if (profesor == null) {
            throw new ProfesorNotFoundException(id);
        }
        return profesor;
    }
}
