package dao;

import exceptions.DAOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Client;
import models.Sale;
import singleton.DatabaseConnection;



public class saleDAOJDBCImplem{

    public ArrayList<Sale> listSalesByClient(Client client, Connection connection) throws DAOException{
        ArrayList<Sale> salesArray = new ArrayList<>();
        client = new Client();
        String selectQuery = "SELECT * FROM sales WHERE client_cif = " +client.getCif();
        
        try {
            connection = DatabaseConnection.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            
            while(resultSet.next()){
                client.setCif(resultSet.getString(0));
                client.setName(resultSet.getString(1));
                client.setDirection(resultSet.getString(2));
                client.setTown(resultSet.getString(3));
                client.setTelephone(resultSet.getString(4));
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void insertSales(String query) throws DAOException{
        
    }
}
