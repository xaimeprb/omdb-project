package psp.omdbapi;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import psp.omdbapi.api.OmdbApiClient;
import psp.omdbapi.dto.MovieDTO;
import psp.omdbapi.mongo.MongoConnection;
import psp.omdbapi.mongo.dao.ActorDAO;
import psp.omdbapi.mongo.dao.MovieDAO;
import psp.omdbapi.mongo.dao.impl.ActorDAOImpl;
import psp.omdbapi.mongo.dao.impl.MovieDAOImpl;
import psp.omdbapi.sql.dao.ActorSQLDAO;
import psp.omdbapi.sql.dao.MovieActorSQLDAO;
import psp.omdbapi.sql.dao.MovieSQLDAO;
import psp.omdb.hibernate.util.LogUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Clase principal del proyecto OMDb que ejecuta el flujo completo: API REST -> MongoBD -> agregaciones -> MySQL
 *
 * @author Jaime Pérez Roget Blanco
 * @since 21/02/2026
 */
public class Omdbapi {

    private static final Logger logger = LogUtil.getLogger(Omdbapi.class);

    public static void main(String[] args) {

        try {

            // Consumo de la API REST
            OmdbApiClient apiClient = new OmdbApiClient(OmdbApiClient.loadApiKey());

            MovieDTO movieDTO = apiClient.getMovieByTitle("Inception");

            List<String> actors = Arrays.stream(movieDTO.getActors().split(",")).map(String::trim).toList();

            logger.info("Película obtenida desde la API: {}", movieDTO.getTitle());

            // Inserción en MongoDB
            Document movieDoc = new Document()
                    .append("imdbId", movieDTO.getImdbId())
                    .append("title", movieDTO.getTitle())
                    .append("year", Integer.parseInt(movieDTO.getYear()))
                    .append("director", movieDTO.getDirector())
                    .append("actors", actors);

            MongoCollection<Document> movies = MongoConnection.getDatabase().getCollection("movies");

            if (movies.find(new Document("imdbId", movieDTO.getImdbId())).first() == null) {

                movies.insertOne(movieDoc);
                logger.info("Película insertada en MongoDB");

            } else {

                logger.warn("La película ya existía en MongoDB");

            }

            // Creación de la colección actors
            MovieDAO movieDAO = new MovieDAOImpl();
            ActorDAO actorDAO = new ActorDAOImpl();

            for (Document movie : movieDAO.findAll()) {

                String imdbId = movie.getString("imdbId");
                List<String> movieActors = movie.getList("actors", String.class);

                for (String actor : movieActors) {

                    actorDAO.addMovieToActor(actor, imdbId);

                }

            }

            logger.info("Colección actors generada correctamente");

            // Agregaciones MongoDB
            movieDAO.getTitlesAndYears().forEach(d -> logger.debug("Título/Año: {}", d.toJson()));

            actorDAO.countMoviesPerActor().forEach(d -> logger.debug("Actor stats: {}", d.toJson()));

            movieDAO.getMoviesWithActors().forEach(d -> logger.debug("Lookup result: {}", d.toJson()));

            // Volcado a MySQL
            MovieSQLDAO movieSQLDAO = new MovieSQLDAO();
            ActorSQLDAO actorSQLDAO = new ActorSQLDAO();
            MovieActorSQLDAO movieActorSQLDAO = new MovieActorSQLDAO();

            for (Document movie : movieDAO.findAll()) {

                movieSQLDAO.insertMovie(movie);

                String imdbId = movie.getString("imdbId");
                List<String> movieActors = movie.getList("actors", String.class);

                for (String actor : movieActors) {

                    int actorId = actorSQLDAO.getOrCreateActorId(actor);
                    movieActorSQLDAO.insertRelation(imdbId, actorId);

                }

            }

            logger.info("Datos volcados correctamente a MySQL");

        } catch (Exception ex) {

            logger.error("Error fatal en la ejecución del proceso OMDb", ex);

        }

    }

}