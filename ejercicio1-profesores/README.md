# API de Profesores (versión simple)

Spring Boot + Java 17. Almacenamiento en memoria (ArrayList).

    mvn test
    mvn spring-boot:run

Todos los endpoints requieren el header `X-API-KEY: clave-secreta-123`.

| Método | Ruta | Respuesta |
|--------|------|-----------|
| POST | /api/profesores | 201 / 400 |
| GET | /api/profesores?especialidad=X | 200 |
| GET | /api/profesores/{id} | 200 / 404 |
