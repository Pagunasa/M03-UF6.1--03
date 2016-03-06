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

public class SaleDAOImplem{

    public ArrayList<Sale> listSalesByClient(Client client, Connection connection) throws DAOException{
        ArrayList<Sale> salesArray = new ArrayList<>();
        Sale sale = new Sale();
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        
        try {
            connection = DatabaseConnection.getInstance();
            prepareStatement = connection.prepareStatement("SELECT * FROM sales WHERE client_cif = ?");
            prepareStatement.setString(1, client.getCif());
            resultSet = prepareStatement.executeQuery();
            
            while(resultSet.next()){
                sale.setIdSale(resultSet.getInt("idSale"));
                sale.setSaleDate(resultSet.getDate("saleDate"));
                salesArray.add(sale);
            }
            
        }catch(SQLException ex){
            throw new DAOException("Error adding sale in the list");
        }finally{
            if(prepareStatement != null){
                try{
                    prepareStatement.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
            
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
        }
        
        return salesArray;
    }
    
    public void insertSales(Client client, Product product, Sale sale, Connection connection) throws DAOException{
        boolean result = false;
        String validateClientCif = "SELECT * FROM clients WHERE cif = '?'";
        String validateProductId = "SELECT * FROM products AS p, product_sales AS ps WHERE p.idProduct = ? AND p.idProduct = ps.idProduct";
        String validateSaleDate = "SELECT * FROM sales WHERE saleDate = NOW()";
        String insertSale = "INSERT INTO sales (idSale, saleDate, client_cif) VALUES (null,?,?)";
        PreparedStatement prepareStatementClients = null;
        PreparedStatement prepareStatementProducts = null;
        Statement statementSales = null;
        PreparedStatement prepareStatementInsert = null;
        ResultSet resultSetClients = null;
        ResultSet resultSetProducts = null;
        ResultSet resultSetSales = null;
        
        try{
            connection = DatabaseConnection.getInstance();
            //Clients validator
            prepareStatementClients = connection.prepareStatement(validateClientCif);
            prepareStatementClients.setString(1, client.getCif());
            resultSetClients = prepareStatementClients.executeQuery();
            //Products validator
            prepareStatementProducts = connection.prepareStatement(validateProductId);
            prepareStatementProducts.setInt(1, product.getIdProduct());
            resultSetProducts = prepareStatementProducts.executeQuery();
            //Sales validator
            statementSales = connection.createStatement();
            resultSetSales = statementSales.executeQuery(validateSaleDate);
            
            
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
            
            //Insert sales on database
            try{
                if(result){
                    prepareStatementInsert = connection.prepareStatement(insertSale);
                    prepareStatementInsert.setDate(1, sale.getSaleDate());
                    prepareStatementInsert.setString(2, client.getCif());

                    int rows = prepareStatementInsert.executeUpdate(insertSale);

                    System.out.println(rows+ " have been updated");
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
            }finally{
                if(prepareStatementInsert != null){
                    try{
                        prepareStatementInsert.close();
                    }catch(SQLException ex){
                        System.out.println(ex.getMessage());
                        System.out.println(ex.getErrorCode());
                    }
                }
            }
            
        }catch(SQLException ex){
            throw new DAOException("Error adding sale");
        }finally{
            if(prepareStatementClients != null){
                try{
                    prepareStatementClients.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
            
            if(resultSetClients != null){
                try{
                    resultSetClients.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
            
            if(prepareStatementProducts != null){
                try{
                    prepareStatementProducts.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
            
            if(resultSetProducts != null){
                try{
                    resultSetProducts.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
            
            if(statementSales != null){
                try{
                    statementSales.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
            
            if(resultSetSales != null){
                try{
                    resultSetSales.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
        }
    }
}
