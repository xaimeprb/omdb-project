package psp.omdbapi.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Clase de utilidad encargada de gestionar la conexión con la base de datos MongoDB,
 * proporcionando una única instancia de MongoDatabase para toda la aplicación.
 *
 * @author Jaime Pérez Roget Blanco
 */
public class MongoConnection {

    private static MongoDatabase database;

    private MongoConnection() {}

    /**
     * Obtiene la instancia de la base de datos MongoDB.
     * Si no existe, crea la conexión y la inicializa.
     *
     * @return instancia de MongoDatabase
     */
    public static MongoDatabase getDatabase() {

        if (database == null) {

            String uri = "mongodb://localhost:27017";
            MongoClient client = MongoClients.create(uri);
            database = client.getDatabase("omdb");

        }

        return database;

    }

}
