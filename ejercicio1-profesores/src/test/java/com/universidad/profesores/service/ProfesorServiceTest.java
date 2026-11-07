package com.universidad.profesores.service;

import com.universidad.profesores.exception.ProfesorNotFoundException;
import com.universidad.profesores.model.Profesor;
import com.universidad.profesores.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfesorServiceTest {

    private ProfesorService service;

    @BeforeEach
    void setUp() {
        service = new ProfesorService(new ProfesorRepository());
    }

    private Profesor crearProfesorEjemplo() {
        Profesor p = new Profesor();
        p.setNombre("Ana");
        p.setApellido("López");
        p.setCorreo("ana@correo.com");
        p.setEspecialidad("Matemática");
        p.setEstado(true);
        return p;
    }

    @Test
    void crearProfesor_debeAsignarId() {
        Profesor creado = service.crear(crearProfesorEjemplo());

        assertNotNull(creado.getId());
        assertEquals("Ana", creado.getNombre());
    }

    @Test
    void obtenerProfesorExistente_debeRetornarlo() {
        Profesor creado = service.crear(crearProfesorEjemplo());

        Profesor encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void obtenerProfesorInexistente_debeLanzarExcepcion() {
        assertThrows(ProfesorNotFoundException.class, () -> service.obtenerPorId(999L));
    }
}
