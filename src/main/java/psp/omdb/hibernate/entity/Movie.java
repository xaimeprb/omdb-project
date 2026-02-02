package psp.omdb.hibernate.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Movie.
 * Representa una película del sistema y su relación con los actores
 * mediante la entidad intermedia MovieActor.
 *
 * @author Jaime Pérez Roget Blanco
 */
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "imdb_id", length = 15)
    private String imdbId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int year;

    private String director;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MovieActor> movieActors = new HashSet<>();

    public Movie() {}

    public String getImdbId() { return imdbId; }
    public void setImdbId(String imdbId) { this.imdbId = imdbId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Set<MovieActor> getMovieActors() { return movieActors; }
    public void setMovieActors(Set<MovieActor> movieActors) { this.movieActors = movieActors; }
}
