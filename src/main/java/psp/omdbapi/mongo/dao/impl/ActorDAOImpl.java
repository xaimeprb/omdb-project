package psp.omdbapi.mongo.dao.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import psp.omdbapi.mongo.MongoConnection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import psp.omdbapi.mongo.dao.ActorDAO;


/**
 * Implementación del DAO de actores para MongoDB.
 * Gestiona la persistencia de actores y su relación con películas.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 02/02/2026
 */
public class ActorDAOImpl implements ActorDAO {

    private final MongoCollection<Document> collection;

    /**
     * Inicializa el DAO obteniendo la colección de actores
     * desde la base de datos MongoDB.
     */
    public ActorDAOImpl() {
        this.collection = MongoConnection
                .getDatabase()
                .getCollection("actors");
    }

    /**
     * Asocia una película a un actor. Si el actor no existe,
     * se crea automáticamente mediante upsert.
     *
     * @param actorName nombre del actor
     * @param imdbId identificador IMDB de la película
     */
    @Override
    public void addMovieToActor(String actorName, String imdbId) {

        collection.updateOne(
                eq("name", actorName),
                new Document("$addToSet",
                        new Document("movies", imdbId)),
                new com.mongodb.client.model.UpdateOptions().upsert(true)
        );

    }

    /**
     * Devuelve el número de películas asociadas a cada actor
     * mediante una agregación sobre la colección.
     *
     * @return lista de documentos con el nombre del actor y el total de películas
     */
    public List<Document> countMoviesPerActor() {

        List<Document> result = new ArrayList<>();

        collection.aggregate(List.of(
                new Document("$project",
                        new Document("name", 1)
                                .append("movieCount",
                                        new Document("$size", "$movies"))
                                .append("_id", 0)
                )

        )).into(result);

        return result;

    }

}
