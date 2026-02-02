package psp.omdbapi.mongo.dao.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import psp.omdbapi.mongo.MongoConnection;

import java.util.ArrayList;
import java.util.List;
import psp.omdbapi.mongo.dao.MovieDAO;

/**
 * Implementación del DAO de películas para MongoDB.
 * Gestiona el acceso y las operaciones de consulta sobre la colección movies.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 02/02/2026
 */
public class MovieDAOImpl implements MovieDAO {

    private final MongoCollection<Document> collection;

    /**
     * Inicializa el DAO obteniendo la colección de películas
     * desde la base de datos MongoDB.
     */
    public MovieDAOImpl() {

        this.collection = MongoConnection.getDatabase().getCollection("movies");

    }

    /**
     * Recupera todas las películas almacenadas en la colección movies.
     *
     * @return lista de documentos de películas
     */
    @Override
    public List<Document> findAll() {

        List<Document> movies = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {

            while (cursor.hasNext()) {

                movies.add(cursor.next());

            }

        }

        return movies;
    }

    /**
     * Obtiene el título y el año de estreno de todas las películas
     * mediante una proyección.
     *
     * @return lista de documentos con título y año
     */
    public List<Document> getTitlesAndYears() {

        List<Document> result = new ArrayList<>();

        collection.aggregate(List.of(
                new Document("$project",
                        new Document("title", 1)
                                .append("year", 1)
                                .append("_id", 0)
                )
        )).into(result);

        return result;

    }

    /**
     * $lookup
     * @return
     */
    public List<Document> getMoviesWithActors() {

        List<Document> result = new ArrayList<>();

        collection.aggregate(List.of(
                new Document("$lookup",
                        new Document("from", "actors")
                                .append("localField", "actors")
                                .append("foreignField", "name")
                                .append("as", "actorsInfo")
                )
        )).into(result);

        return result;
    }

}
