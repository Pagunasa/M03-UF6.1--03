package dao;

import exceptions.DAOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Client;
import singleton.DatabaseConnection;

public class clientDAOJDBCImplem implements interfaceDAO<Client, String>{ 

    @Override
    public ArrayList<Client> list(Connection connection) throws DAOException {
        try {
            ArrayList<Client> clients = new ArrayList();
            DatabaseConnection.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(clientDAOJDBCImplem.class.getName()).log(Level.SEVERE, null, ex);
        }
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
