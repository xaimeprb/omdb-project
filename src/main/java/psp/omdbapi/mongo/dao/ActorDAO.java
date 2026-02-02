package psp.omdbapi.mongo.dao;

import org.bson.Document;
import java.util.List;

/**
 * Interfaz DAO que define las operaciones de acceso a datos
 * relacionadas con los actores almacenados en MongoDB.
 */
public interface ActorDAO {

    /**
     * Asocia una película a un actor, creando el actor si no existe
     * y añadiendo el identificador de la película.
     *
     * @param actorName nombre del actor
     * @param imdbId identificador IMDB de la película
     */
    void addMovieToActor(String actorName, String imdbId);

    /**
     * Realiza una agregación que devuelve el número de películas
     * en las que ha participado cada actor.
     *
     * @return lista de documentos con el recuento de películas por actor
     */
    List<Document> countMoviesPerActor();

}
