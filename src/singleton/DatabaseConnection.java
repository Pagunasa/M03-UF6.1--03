package singleton;

import java.sql.Connection;

public class DatabaseConnection {
    
    private static Connection connection;
    
    private DatabaseConnection(){
        
    }
    
    public static void getInstance(){ //Falta añadir el throws SQLException

    }
    
    public static void closeConnection(){ //Falta añadir el throws SQLException
        
    }
}
