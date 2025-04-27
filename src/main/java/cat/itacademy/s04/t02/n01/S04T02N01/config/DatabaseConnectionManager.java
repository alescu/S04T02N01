package cat.itacademy.s04.t02.n01.S04T02N01.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    static private DatabaseConnectionManager dataManagerInstance = null;
    static private Connection conn = null;

    private DatabaseConnectionManager() throws SQLException {
        conn = DriverManager.
                getConnection("jdbc:h2:~/test", "root", "password");
    }

    public static synchronized Connection getConnection() {
        if (dataManagerInstance==null){
            try {
                dataManagerInstance = new DatabaseConnectionManager();
            } catch (SQLException e) {
                System.out.println(e);
                conn=null;
            }
        }
        return conn;
    }

   public static void closeConnection(){
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}
