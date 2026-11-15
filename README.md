# Customers

API simple para registrar y consultar clientes, con configuración por ambiente (dev y prod).
Hecha con Java 17 y Spring Boot.

## Requisitos
- Java 17 o superior
- Docker (solo para la parte opcional)

## Construir
Windows:

    mvnw.cmd clean package

Linux / Mac:

    ./mvnw clean package

Genera el ejecutable `target/customers-0.0.1-SNAPSHOT.jar`.

## Ejecutar en DEV
    java -jar target/customers-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

## Ejecutar en PROD (simulado)
    java -jar target/customers-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

## Diferencias entre ambientes
La configuración vive en `src/main/resources`:

|   Configuración  |                DEV           |      PROD                     |
|------------------|------------------------------|-------------------------------|
| Archivo          | `application-dev.properties` | `application-prod.properties` |
| Puerto           | 8080                         | 9090                          |
| Nombre de la app | `customers-dev`              | `customers-prod`              |
| Mensaje de log   | Ejecutando en DEV            | Ejecutando en PROD            |

Al arrancar, el log muestra el nombre y el mensaje del ambiente activo. Si no se indica perfil, se usa `dev` (definido en `application.properties`).

## Endpoints
- `POST /api/customers` con body `{"name":"Juan Perez","email":"juan@email.com"}`
- `GET /api/customers`
- `GET /api/info` (nombre, mensaje y puerto del ambiente activo)

Ejemplo en PowerShell:

    Invoke-RestMethod -Method Post -Uri http://localhost:8080/api/customers -ContentType "application/json" -Body '{"name":"Juan Perez","email":"juan@email.com"}'
    Invoke-RestMethod -Uri http://localhost:8080/api/customers

Ejemplo con curl (Linux / Mac):

    curl -X POST http://localhost:8080/api/customers -H "Content-Type: application/json" -d '{"name":"Juan Perez","email":"juan@email.com"}'
    curl http://localhost:8080/api/customers

Los datos se guardan en memoria, por lo que se pierden al reiniciar la aplicación.

## Interfaz y documentación
- Interfaz web: `http://localhost:8080` (dev) y `http://localhost:9090` (prod)
- Swagger UI: `http://localhost:8080/swagger-ui.html` (dev) y `http://localhost:9090/swagger-ui.html` (prod)

## Docker (opcional)
La imagen compila el proyecto por dentro, así que no hace falta generar el `.jar` antes.

    docker build -t customers .
    docker run --rm -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev customers
    docker run --rm -p 9090:9090 -e SPRING_PROFILES_ACTIVE=prod customers

Es la misma imagen en ambos casos; solo cambia el perfil que se elige con la variable de entorno.