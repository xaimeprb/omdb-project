package psp.omdbapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import psp.omdbapi.dto.MovieDTO;
import psp.omdb.hibernate.util.LogUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * Cliente encargado de consumir la API REST de OMDb para obtener información
 * de películas y mapear la respuesta a objetos de dominio.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 02/02/2026
 */
public class OmdbApiClient {

    private static final Logger logger = LogUtil.getLogger(OmdbApiClient.class);

    private static final String API_URL = "https://www.omdbapi.com/";
    private final String apiKey;
    private final HttpClient client;
    private final ObjectMapper mapper;

    /**
     * Construye un cliente de acceso a la API de OMDb utilizando la API Key proporcionada.
     *
     * @param apiKey clave de acceso a la API de OMDb
     */
    public OmdbApiClient(String apiKey) {

        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();

    }

    /**
     * Obtiene la información de una película a partir de su título,
     * realizando una petición HTTP GET a la API de OMDb.
     *
     * @param title título de la película a buscar
     * @return objeto MovieDTO con los datos de la película
     * @throws Exception si se produce un error en la petición o el mapeo
     */
    public MovieDTO getMovieByTitle(String title) throws Exception {

        String url = API_URL + "?apikey=" + apiKey + "&t=" + title.replace(" ", "+");

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), MovieDTO.class);

    }

    /**
     * Obtiene la API Key de OMDb desde las variables de entorno.
     *
     * @return API key
     * @throws IllegalStateException si no está definida
     */
    public static String loadApiKey() {

        String apiKey = System.getenv("OMDB_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {

            logger.error("OMDB_API_KEY no definida en el entorno");

            throw new IllegalStateException(
                    "Falta la variable de entorno OMDB_API_KEY"
            );
        }

        logger.info("API Key de OMDb cargada correctamente");

        return apiKey;
    }

}
