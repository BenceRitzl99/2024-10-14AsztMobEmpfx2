package lan.zold;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sqlite implements Database {
    public Connection connect() {
        try {
            return tryConnect();
        } catch (SQLException e) {
            System.err.println("Hiba! A kapcsolódás sikertelen!");
            System.err.println(e.getMessage());
            return null;
        }        
    }

    public Connection tryConnect() throws SQLException {
        String url = "jdbc:sqlite:database.sqlite";
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
}
