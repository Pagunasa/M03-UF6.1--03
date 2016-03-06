package dao;		
 		
import exceptions.DAOException;		
import java.sql.Connection;		
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Product;	

public class ProductDAOImplem implements InterfaceDAOGeneral<Product, Integer>{		

    //Inherited method from InterfaceDAO		
    @Override		
    public ArrayList<Product> list(Connection connection) throws DAOException {		
        ArrayList<Product> products = new ArrayList();
        String queryAllClients = "SELECT * FROM products";		
        Statement statement = null;		
        ResultSet resultSet = null;
        
        try {		
            statement = connection.createStatement();				
            resultSet = statement.executeQuery(queryAllClients);
            
            while (resultSet.next()){
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("idProduct"));
                product.setDescription(resultSet.getString("description"));
                product.setCurrentStock(resultSet.getInt("currentStock"));
                product.setPvp(resultSet.getDouble("pvp"));
                
                //Add the client to ArrayList<Product> 
                products.add(product);
            }		

        }catch (SQLException ex) {		
            throw new DAOException("Error listing clients");		
        }finally{
            //Closing Statement
            if(statement != null){
                try{
                    statement.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
            
            //Closing ResultSet
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                }
            }
        }
        
        return products;		
    }		

}
