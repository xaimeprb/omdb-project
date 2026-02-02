package psp.omdbapi.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilidad de conexión JDBC a MySQL.
 * 
 * Proporciona conexiones bajo demanda.
 * La gestión del ciclo de vida se delega al DAO.
 */
public class MySQLConnection {

    // Esta URL elimina el error de MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/omdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    private MySQLConnection() { }

    public static Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    
    }

}
