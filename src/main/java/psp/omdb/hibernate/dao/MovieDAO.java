package psp.omdb.hibernate.dao;

import java.util.List;
import psp.omdb.hibernate.entity.Movie;

/**
 * <h1>DAO de Movie</h1>
 *
 * Define las operaciones de acceso a datos para la entidad {@link Movie}.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 27/01/2026
 */
public interface MovieDAO {

    /**
     * Guarda o actualiza una película.
     *
     * @param movie entidad Movie
     */
    void save(Movie movie);

    /**
     * Busca una película por su imdbId.
     *
     * @param imdbId identificador IMDb
     * @return película o null
     */
    Movie findByImdbId(String imdbId);

    /**
     * Recupera todas las películas.
     *
     * @return lista de películas
     */
    List<Movie> findAll();

}
