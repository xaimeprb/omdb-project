package psp.omdbapi.mongo.model;

import java.util.List;

public class MovieDocument {

    private String imdbId;
    private String title;
    private int year;
    private String director;
    private List<String> actors;

    public MovieDocument() {}

    public MovieDocument(String imdbId, String title, int year, String director, List<String> actors) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
    }

    public String getImdbId() { return imdbId; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public String getDirector() { return director; }
    public List<String> getActors() { return actors; }
    
}
