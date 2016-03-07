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
import models.ProductSale;
import models.Sale;
import singleton.DatabaseConnection;

public class SaleDAOImplem{

    
    public ArrayList<Sale> listSalesByClient(Client client, Connection connection) throws DAOException{
        ArrayList<Sale> salesArray = new ArrayList<>();
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        
        try {
            connection = DatabaseConnection.getInstance();
            prepareStatement = connection.prepareStatement("SELECT * FROM sales WHERE client_cif = ?");
            prepareStatement.setString(1, client.getCif());
            resultSet = prepareStatement.executeQuery();
            
            while(resultSet.next()){
                Sale sale = new Sale();
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
    
    public void insertSales(Client client, Product product, Sale sale, ProductSale ps, Connection connection) throws DAOException{
        boolean result = false;
        
        
        PreparedStatement prepareStatementClients = null;
        PreparedStatement prepareStatementProducts = null;
        PreparedStatement prepareStatementProductSale = null;
        Statement statementSales = null;
        PreparedStatement prepareStatementInsert = null;
        ResultSet resultSetClients = null;
        ResultSet resultSetProducts = null;
        ResultSet resultSetSales = null;
        ResultSet resultSetInsertSales = null;
        ResultSet resultSetPS = null;
        String cantidad;
        
        try{
            //connection = DatabaseConnection.getInstance();
            //Clients validator
            prepareStatementClients = connection.prepareStatement("SELECT * FROM clients WHERE cif = ?");
            prepareStatementClients.setString(1, client.getCif());
            resultSetClients = prepareStatementClients.executeQuery();
            //Products validator
            prepareStatementProducts = connection.prepareStatement("SELECT * FROM products AS p, products_sales AS ps WHERE p.idProduct = ? AND p.idProduct = ps.idProduct");
            prepareStatementProducts.setInt(1, product.getIdProduct());
            resultSetProducts = prepareStatementProducts.executeQuery();
            
            //Sales validator
            statementSales = connection.createStatement();
            resultSetSales = statementSales.executeQuery("SELECT * FROM sales WHERE saleDate = NOW()");

            
            if(resultSetClients.next()){
                result = true;
            }else {
                System.out.println("Client CIF " +client.getCif()+ " does not exist");
                result = false;
            }
            
            if(resultSetProducts.next()){
               if (result) {result = true;}
            }else{
                System.out.println("Product ID " +product.getIdProduct()+ " does not exist");
                result = false;
            }
            
            if(resultSetProducts.getInt("currentStock") > 0){
               if (result) {result = true;}
            }else{
                System.out.println("Stock must be higher than zero");
                result = false;
            }
            
           /* if(resultSetSales.next()){
                result = true;
            }else{
                System.out.println("Incorrect date");
                result = false;
            }*/
            
            if(ps.getQuantity() > resultSetProducts.getInt("currentStock")){
                System.out.println("We do not have enough stock sorry");
                result = false;
            } else if (ps.getQuantity() <= 0){
                System.out.println("We can't sell negative quantities");
                result = false;
            } else {
               if (result) {result = true;}
            }
            
            //Insert sales on database
            try{
                if(result){
                    prepareStatementInsert = connection.prepareStatement("INSERT INTO sales (idSale, saleDate, client_cif) VALUES (null,NOW(),?)");
                    prepareStatementInsert.setString(1, client.getCif());

                    int rows = prepareStatementInsert.executeUpdate();
                    
                    //Luego lo retiramos
                    System.out.println(rows+ " rows have been updated");
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
            result = false;
            System.out.println(ex.getMessage());
            System.out.println(ex.getErrorCode());
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
            
            try{
                if(result){

                    Statement statement = null;
                    PreparedStatement updateStock = null;
                    ResultSet resultSet = null;
                    int idSale = 0;
                    
                    statement = connection.createStatement();				
                    resultSet = statement.executeQuery("SELECT * FROM sales ORDER BY idSale DESC LIMIT 1 ");
                   
                    while (resultSet.next()){
                        idSale = resultSet.getInt("idSale");
                    }
                    
                    prepareStatementProductSale = connection.prepareStatement("INSERT INTO products_sales (idProduct, idSale, quantity) VALUES (?,?,?)");
                    prepareStatementProductSale.setInt(1, product.getIdProduct());
                    prepareStatementProductSale.setInt(2, idSale);
                    prepareStatementProductSale.setInt(3, ps.getQuantity());
                    
                    int nuevoStock = resultSetProducts.getInt("currentStock") - ps.getQuantity();
                    
                    updateStock = connection.prepareStatement("UPDATE products SET currentStock = ? WHERE idProduct = ?");
                    updateStock.setInt(1, nuevoStock);
                    updateStock.setInt(2, product.getIdProduct());
                    
                    int rows2 = updateStock.executeUpdate();
                    int rows = prepareStatementProductSale.executeUpdate();
                    
                    //Luego lo retiramos
                    System.out.println(rows+rows2+" rows have been updated");
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
            }finally{
                if(prepareStatementProductSale != null){
                    try{
                        prepareStatementProductSale.close();
                    } catch(SQLException ex){
                        System.out.println(ex.getMessage());
                        System.out.println(ex.getErrorCode()); 
                    }
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
