package m03.uf6.pkg1.pkg03;

import dao.DAOFactory;
import dao.SaleDAOImplem;
import exceptions.DAOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Client;
import singleton.DatabaseConnection;

public class M03UF6103 {

    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
       
    //Variables
    DAOFactory saleFactory = new DAOFactory();
    SaleDAOImplem saleDAOImplem = saleFactory.createSaleDAO();
    
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
                        //llamar a ver clientes
                        break;
                     case 2:
                        //llamar a ver productos
                        break;
                     case 3:

                        break;
                     case 4:
                        System.out.println("Enter a costumer CIF: ");
                        String cif = scanner.nextLine();
                         
                        Client client = new Client(cif);
                        try{
                            saleDAOImplem.listSalesByClient(client, DatabaseConnection.getInstance());
                        }catch(DAOException ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                     case 5:
                        System.out.println("Gracias por usar la aplicación!!");
                        System.exit(0);
                        
                        try{
                            DatabaseConnection.closeConnection();
                        }catch(SQLException ex){
                            System.out.println(ex.getMessage());
                            System.out.println(ex.getErrorCode());
                        }
                        break;
                     default:
                        System.out.println("Opción no valida. Selecciona otra porfavor");
                        break;
                 }
            } while(option != 5);
       
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getErrorCode());
        }
    }

}
