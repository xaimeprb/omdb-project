package psp.omdb.hibernate.entity;

import jakarta.persistence.*;

/**
 * Entidad intermedia MovieActor.
 * Representa la relación N:M entre películas y actores.
 *
 * @author Jaime Pérez Roget Blanco
 */
@Entity
@Table(name = "movie_actor")
public class MovieActor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "imdb_id")
    private Movie movie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "actor_id")
    private Actor actor;

    public MovieActor() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public Actor getActor() { return actor; }
    public void setActor(Actor actor) { this.actor = actor; }
}
