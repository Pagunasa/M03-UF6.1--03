package dao;

import exceptions.DAOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Client;
import singleton.DatabaseConnection;

public class clientDAOJDBCImplem implements interfaceDAO<Client, String>{ 

    @Override
    public ArrayList<Client> list(Connection connection) throws DAOException {
   
        ArrayList<Client> clients = new ArrayList();
        String queryAllClients = "SELECT * FROM clients";
        
        try {
            connection = DatabaseConnection.getInstance();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(queryAllClients);
            Client client = new Client();
            
            while (rs.next()){
                client.setCif(rs.getString("cif"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(clientDAOJDBCImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
