package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionUtil {
    private static ConnectionUtil instance;
    private ConnectionUtil() { super(); }
    public static ConnectionUtil getInstance() {
        if(instance == null) {
            instance = new ConnectionUtil();
        }
        return instance;
    }
    public static Connection getConnection() throws SQLException {
        try {

            String dbInfo = "jdbc:postgresql://localhost/postgres?user=postgres&password=revature";
            return DriverManager.getConnection(dbInfo);


        }  catch (SQLException e) {

            e.printStackTrace();
        }  catch (Throwable t) {

        }
        return null;
    }




}
