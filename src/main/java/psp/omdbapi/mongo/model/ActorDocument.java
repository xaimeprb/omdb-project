package psp.omdbapi.mongo.model;

import java.util.List;

public class ActorDocument {

    private String name;
    private List<String> movies;

    public ActorDocument() {}

    public ActorDocument(String name, List<String> movies) {
        this.name = name;
        this.movies = movies;
    }

    // getters y setters
}
