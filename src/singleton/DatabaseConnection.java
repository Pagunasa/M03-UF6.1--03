package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    //Instance variable
    private static Connection instance;
    
    //Necessary empty constructor
    private DatabaseConnection(){}
    
    //getInstance Method
    public static Connection getInstance() throws SQLException{
        if (instance == null){
            instance = DriverManager.getConnection(MYSQLConnection.servername,
                    MYSQLConnection.username,
                    MYSQLConnection.password);
            System.out.println("Database Opened");
        }
        return instance;
    }
    
    //Close the connection to database
    public static void closeConnection() throws SQLException{
        if (instance != null){
            instance.close();
            instance = null;
            System.out.println("Database Closed");
        }
    }
}
