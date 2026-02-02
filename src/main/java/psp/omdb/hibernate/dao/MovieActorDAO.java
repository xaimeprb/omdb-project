package psp.omdb.hibernate.dao;

import java.util.List;
import psp.omdb.hibernate.entity.MovieActor;

/**
 * <h1>DAO de MovieActor</h1>
 *
 * Gestiona la tabla intermedia entre Movie y Actor.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 27/01/2026
 */
public interface MovieActorDAO {

    /**
     * Guarda una relación Movie-Actor.
     *
     * @param movieActor relación MovieActor
     */
    void save(MovieActor movieActor);

    /**
     * Recupera todas las relaciones MovieActor.
     *
     * @return lista de relaciones
     */
    List<MovieActor> findAll();

    /**
     * Consulta las películas con sus actores usando JOIN.
     *
     * @return lista de resultados Object[]
     */
    List<Object[]> findMoviesWithActors();

}
