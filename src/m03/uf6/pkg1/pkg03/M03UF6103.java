package m03.uf6.pkg1.pkg03;

import dao.ClientDAOImplem;
import dao.DAOFactory;
import dao.ProductDAOImplem;
import dao.SaleDAOImplem;
import exceptions.DAOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import models.Client;
import models.Product;
import models.Sale;
import singleton.DatabaseConnection;

public class M03UF6103 {

    static Scanner scanner = new Scanner(System.in);
    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) { 
    //Variables
    DAOFactory saleFactory = new DAOFactory();
    SaleDAOImplem saleDAOImplem = saleFactory.createSaleDAO();
    ArrayList<Sale> salesArray;
    DAOFactory clientFactory = new DAOFactory();
    ClientDAOImplem clientDAOImplem = clientFactory.createClientDAO();
    ArrayList<Client> clientsArray;
    DAOFactory productFactory = new DAOFactory();
    ProductDAOImplem productDAOImplem = productFactory.createProductDAO();
    ArrayList<Product> productsArray;
    
   
        try{
            DatabaseConnection.getInstance();

            int option;

            do{
                System.out.println("Choose option:\n1.- See all clients" +
                                   "\n2.- See all products\n" +
                                   "3.- Insert sales\n" +
                                   "4.- List sales of a client\n" +
                                   "5.- Exit");

                option = scanner.nextInt();            

                switch(option){
                    case 1:
                        try{
                            clientsArray = clientDAOImplem.list(DatabaseConnection.getInstance());
                            
                            if(clientsArray.isEmpty()){
                                System.out.println("Table clients it's empty!");
                            }else{
                                for(Client client : clientsArray){
                                    System.out.println("Client CIF: " +client.getCif());
                                    System.out.println("\tName: " +client.getName());
                                    System.out.println("\tDirection: " +client.getDirection());
                                    System.out.println("\tTown: " +client.getTown());
                                    System.out.println("\tTelephone: " +client.getTelephone());
                                }
                            }
                        }catch(DAOException ex){
                            System.out.println("Error listing clients!");
                        }
                       break;
                    case 2:
                       try{
                           productsArray = productDAOImplem.list(DatabaseConnection.getInstance());
                           
                           if(productsArray.isEmpty()){
                               System.out.println("Table products it's empty");
                           }else{
                               for(Product product : productsArray){
                                   System.out.println("Product ID: " +product.getIdProduct());
                                   System.out.println("\tDescription: " +product.getDescription());
                                   System.out.println("\tCurrent stock: " +product.getCurrentStock());
                                   System.out.println("\tPVP: " +product.getPvp());
                               }
                           }
                       }catch(DAOException ex){
                           System.out.println("Error listing products");
                       }
                       break;
                    case 3:
                       break;
                    case 4:
                        try{
                            System.out.println("Enter a costumer CIF: ");
                            String cif = stdin.readLine();
                            
                            Client client = new Client();
                            client.setCif(cif);

                            salesArray = saleDAOImplem.listSalesByClient(client, DatabaseConnection.getInstance());
                            
                            for(Sale sale : salesArray){
                                System.out.println("Sale: " +sale.getIdSale());
                            }
                        }catch(IOException | DAOException ex){
                            System.out.println(ex.getMessage());
                        }
                       break;
                    case 5:
                       System.out.println("Thanks to use our application!!");
                       System.exit(0);
                       break;
                    default:
                       System.out.println("Not valid option!");
                       break;
                }
            } while(option != 5);
       
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getErrorCode());
        }finally{
            try{
                DatabaseConnection.closeConnection();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
            }
        }
    }

}
