package m03.uf6.pkg1.pkg03;

import java.sql.SQLException;
import java.util.Scanner;
import singleton.DatabaseConnection;

public class M03UF6103 {

    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
       
        try{
            DatabaseConnection.getInstance();

            int option;


            do{

                System.out.println("Elige opción:\n1.- Ver Clientes" +
                                   "\n2.- Ver Productos\n" +
                                   "3.- Insertar Ventas\n" +
                                   "4.- Consultar Ventas de un Ciente\n" +
                                   "5.- Salir");

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
                         //llamar a consultar ventas de un cliente
                         break;
                     case 5:
                         System.out.println("Gracias por usar la aplicación!!");
                         System.exit(0);
                         break;
                     default:
                         System.out.println("Opción no valida. Selecciona otra porfavor");
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
