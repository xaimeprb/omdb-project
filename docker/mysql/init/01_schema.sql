-- Base de datos OMDb

CREATE DATABASE IF NOT EXISTS omdb
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
USE omdb;

-- Tabla MOVIES
CREATE TABLE IF NOT EXISTS movies (
    imdb_id VARCHAR(15) NOT NULL,
    title VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    director VARCHAR(255),
    CONSTRAINT pk_movies PRIMARY KEY (imdb_id)
);

-- Tabla ACTORS
CREATE TABLE IF NOT EXISTS actors (
    actor_id INT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_actors PRIMARY KEY (actor_id),
    CONSTRAINT uk_actor_name UNIQUE (name)
);

-- Tabla MOVIE_ACTOR (N:M)
CREATE TABLE IF NOT EXISTS movie_actor (
id INT AUTO_INCREMENT PRIMARY KEY,
imdb_id  VARCHAR(15) NOT NULL,
actor_id INT NOT NULL,
    CONSTRAINT fk_movie_actor_movie
    FOREIGN KEY (imdb_id)
    REFERENCES movies (imdb_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT fk_movie_actor_actor
    FOREIGN KEY (actor_id)
    REFERENCES actors (actor_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
