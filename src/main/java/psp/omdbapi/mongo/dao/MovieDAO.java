package psp.omdbapi.mongo.dao;

import org.bson.Document;
import java.util.List;

/**
 * Interfaz DAO que define las operaciones de acceso a datos
 * relacionadas con las películas almacenadas en MongoDB.
 */
public interface MovieDAO {

    /**
     * Obtiene todas las películas almacenadas en la colección.
     *
     * @return lista de documentos de películas
     */
    List<Document> findAll();

    /**
     * Recupera el título y el año de estreno de todas las películas.
     *
     * @return lista de documentos con título y año
     */
    List<Document> getTitlesAndYears();

    /**
     * Obtiene las películas junto con sus actores asociados
     * mediante una operación de agregación.
     *
     * @return lista de documentos con películas y actores
     */
    List<Document> getMoviesWithActors();

}
