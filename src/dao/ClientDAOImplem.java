package dao;		

import exceptions.DAOException;		
import java.sql.Connection;		
import java.sql.ResultSet;		
import java.sql.SQLException;		
import java.sql.Statement;		
import java.util.ArrayList;			
import models.Client;		
import singleton.DatabaseConnection;		

public class ClientDAOImplem implements InterfaceDAOGeneral<Client, String>{ 		

    //Inherited method from InterfaceDAO		
    @Override		
    public ArrayList<Client> list(Connection connection) throws DAOException {		

        ArrayList<Client> clients = new ArrayList();
        String queryAllClients = "SELECT * FROM clients";		
        Statement statement = null;		
        ResultSet resultSet = null;
        
        try {		
            connection = DatabaseConnection.getInstance();		
            statement = connection.createStatement();				
            resultSet = statement.executeQuery(queryAllClients);
            
            while (resultSet.next()){
                Client client = new Client();
                client.setCif(resultSet.getString("cif"));				
                client.setName(resultSet.getString("name"));
                client.setDirection(resultSet.getString("direction"));				
                client.setTown(resultSet.getString("town"));		
                client.setTelephone(resultSet.getString("telephone"));	
                
                //Add the client to ArrayList<Client> 
                clients.add(client);		
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
        
        return clients;		
    }		
}
