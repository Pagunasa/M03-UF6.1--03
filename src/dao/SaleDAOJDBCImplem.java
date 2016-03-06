package dao;

import exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Client;
import models.Product;
import models.Sale;
import singleton.DatabaseConnection;

public class SaleDAOJDBCImplem{

    public ArrayList<Sale> listSalesByClient(Client client, Connection connection) throws DAOException{
        ArrayList<Sale> salesArray = new ArrayList<>();
        Sale sale = new Sale();
        String selectQuery = "SELECT * FROM sales WHERE client_cif = ?";
        
        try {
            connection = DatabaseConnection.getInstance();
            PreparedStatement prepareStatement = connection.prepareStatement(selectQuery);
            prepareStatement.setString(1, client.getCif());
            ResultSet resultSet = prepareStatement.executeQuery(selectQuery);
            
            while(resultSet.next()){
                sale.setIdSale(resultSet.getInt("idSale"));
                sale.setSaleDate(resultSet.getDate("saleDate"));
            }
            
            sale.setClientCif(client.getCif());
            
            salesArray.add(sale);
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());
        }
        
        return salesArray;
    }
    
    public void insertSales(Client client, Product product, Sale sale, Connection connection) throws DAOException{
        boolean result = false;
        String validateClientCif = "SELECT * FROM clients WHERE cif = ?";
        String validateProductId = "SELECT * FROM products AS p, product_sales AS ps WHERE p.idProduct = ? AND p.idProduct = ps.idProduct";
        String validateSaleDate = "SELECT * FROM sales WHERE saleDate = NOW()";
        String insertSale = "INSERT INTO sales (idSale, saleDate, client_cif) VALUES (null,?,?)";
        
        try{
            connection = DatabaseConnection.getInstance();
            //Clients validator
            PreparedStatement prepareStatementClients = connection.prepareStatement(validateClientCif);
            prepareStatementClients.setString(1, client.getCif());
            ResultSet resultSetClients = prepareStatementClients.executeQuery();
            //Products validator
            PreparedStatement prepareStatementProducts = connection.prepareStatement(validateProductId);
            prepareStatementProducts.setInt(1, product.getIdProduct());
            ResultSet resultSetProducts = prepareStatementProducts.executeQuery();
            //Sales validator
            Statement statementSales = connection.createStatement();
            ResultSet resultSetSales = statementSales.executeQuery(validateSaleDate);
            
            
            if(resultSetClients.next()){
                result = true;
            }else {
                System.out.println("Client CIF " +client.getCif()+ " does not exist");
                result = false;
            }
            
            if(resultSetProducts.next()){
               result = true;
            }else{
                System.out.println("Product ID " +product.getIdProduct()+ " does not exist");
                result = false;
            }
            
            if(product.getCurrenStock() > 0){
                result = true;
            }else{
                System.out.println("Stock must be higher than zero");
                result = false;
            }
            
            if(resultSetSales.next()){
                result = true;
            }else{
                System.out.println("Incorrect date");
                result = false;
            }
            
            /*FALTA COMPROBAR LA CANTIDAD*/
            try{
                if(result){
                    PreparedStatement prepareStatement = connection.prepareStatement(insertSale);
                    prepareStatement.setDate(1, sale.getSaleDate());
                    prepareStatement.setString(2, client.getCif());

                    int rows = prepareStatement.executeUpdate(insertSale);

                    System.out.println(rows+ " have been updated");
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());
        }
    }
}
