package psp.omdbapi.sql.dao;

import java.sql.*;
import psp.omdbapi.sql.MySQLConnection;

public class ActorSQLDAO {

    private static final String INSERT_SQL =
        "INSERT INTO actors (name) VALUES (?) " +
        "ON DUPLICATE KEY UPDATE name=name";

    private static final String SELECT_ID_SQL =
        "SELECT actor_id FROM actors WHERE name = ?";

    public int getOrCreateActorId(String name) throws SQLException {

        try (Connection con = MySQLConnection.getConnection()) {

            try (PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
                ps.setString(1, name);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = con.prepareStatement(SELECT_ID_SQL)) {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt("actor_id");
            }
        }
    }
}