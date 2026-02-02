package psp.omdbapi.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bson.Document;
import psp.omdbapi.sql.MySQLConnection;

public class MovieSQLDAO {

    private static final String INSERT_SQL =
        "INSERT INTO movies (imdb_id, title, year, director) " +
        "VALUES (?, ?, ?, ?) " +
        "ON DUPLICATE KEY UPDATE title=title";

    public void insertMovie(Document movie) throws SQLException {

        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            ps.setString(1, movie.getString("imdbId"));
            ps.setString(2, movie.getString("title"));
            ps.setInt(3, movie.getInteger("year"));
            ps.setString(4, movie.getString("director"));

            ps.executeUpdate();
        }
    }
}
