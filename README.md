# OMDb Project – Java, MongoDB, MySQL & Docker

Proyecto Java que consume la API pública de OMDb, persiste la información en MongoDB y MySQL,
y demuestra el uso conjunto de APIs REST, bases de datos NoSQL y SQL, DAOs, Hibernate y Docker.

## Tecnologías utilizadas

- Java 17
- Maven
- MongoDB 7
- MySQL 8
- Hibernate ORM
- Jackson
- SLF4J + Logback
- Docker y Docker Compose

## Funcionalidades principales

- Consumo de la API REST de OMDb
- Inserción de películas en MongoDB
- Gestión de actores y relaciones película–actor
- Agregaciones avanzadas en MongoDB:
  - $project
  - $lookup
  - $size
- Persistencia relacional en MySQL
- Logging centralizado mediante utilidad común
- Entorno reproducible con Docker

## Estructura del proyecto

omdb-project
│
├── docker-compose.yml
├── pom.xml
└── src
    └── main
        └── java
            └── psp
                └── omdbapi
                    ├── api
                    ├── dto
                    ├── mongo
                    ├── sql
                    ├── util
                    └── Omdbapi.java


## Requisitos previos

- Java 17 o superior
- Maven
- Docker
- Clave de API de OMDb

## Variable de entorno obligatoria

Es obligatorio definir la API Key de OMDb antes de ejecutar el proyecto.

### Windows (PowerShell)

setx OMDB_API_KEY "TU_API_KEY"

Cerrar y volver a abrir la terminal tras ejecutarlo.

### Linux / macOS

export OMDB_API_KEY=TU_API_KEY


## Arranque del entorno con Docker

Desde la raíz del proyecto:

docker compose up -d

Servicios levantados:
- MongoDB → localhost:27017
- MySQL → localhost:3306


## Compilación y ejecución

Compilar el proyecto:

mvn clean compile

Ejecutar la aplicación:

mvn exec:java


## Autor

Jaime Pérez Roget Blanco  
Desarrollo de Aplicaciones Multiplataforma (DAM)
