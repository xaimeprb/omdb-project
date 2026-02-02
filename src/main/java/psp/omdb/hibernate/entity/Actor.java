package psp.omdb.hibernate.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Actor.
 * Representa un actor del sistema y su relación con las películas
 * mediante la entidad intermedia MovieActor.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 27/01/2026
 */
@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private int actorId;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "actor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MovieActor> movieActors = new HashSet<>();

    public Actor() {}

    public int getActorId() { return actorId; }
    public void setActorId(int actorId) { this.actorId = actorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<MovieActor> getMovieActors() { return movieActors; }
    public void setMovieActors(Set<MovieActor> movieActors) { this.movieActors = movieActors; }

}
