package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static Connection connection;
    
    private DatabaseConnection(){}
    
    public static Connection getInstance() throws SQLException{
        if (connection == null){
            connection = DriverManager.getConnection(MYSQLConnection.servername,
                    MYSQLConnection.username,
                    MYSQLConnection.password);
            System.out.println("Database Opened");
        }
        return connection;
    }
    
    public static void closeConnection() throws SQLException{
        if (connection != null){
            connection.close();
            connection = null;
            System.out.println("Database Closed");
        }
    }
}
