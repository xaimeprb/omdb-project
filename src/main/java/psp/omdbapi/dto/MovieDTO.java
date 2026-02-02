package psp.omdbapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO que representa los datos de una película obtenidos desde la API de OMDb.
 * Se utiliza para mapear la respuesta JSON a un objeto Java.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 02/02/2026
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {

    @JsonProperty("imdbID")
    private String imdbId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Actors")
    private String actors;

    public String getImdbId() { return imdbId; }
    public void setImdbId(String imdbId) { this.imdbId = imdbId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getActors() { return actors; }
    public void setActors(String actors) { this.actors = actors; }

}
