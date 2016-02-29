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
                client.setCif(rs.getString(1));
                System.out.println(rs.getString(1));
                client.setDirection(rs.getString(3));
                client.setName(rs.getString(2));
                client.setTown(rs.getString(4));
                client.setTelephone(rs.getString(5));
                clients.add(client);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(clientDAOJDBCImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clients;
    }
}
