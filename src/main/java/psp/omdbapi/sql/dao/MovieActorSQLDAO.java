package psp.omdbapi.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import psp.omdbapi.sql.MySQLConnection;

public class MovieActorSQLDAO {

    private static final String INSERT_SQL =
        "INSERT IGNORE INTO movie_actor (imdb_id, actor_id) VALUES (?, ?)";

    public void insertRelation(String imdbId, int actorId) throws SQLException {

        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            ps.setString(1, imdbId);
            ps.setInt(2, actorId);
            ps.executeUpdate();
        }
    }
}
