package m03.uf6.pkg1.pkg03;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import singleton.DatabaseConnection;

public class M03UF6103 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
       String option = "0";
       
       do{
           
           System.out.println("Elige opción:\n1.- Ver Clientes" +
		              "\n2.- Ver Productos\n" +
                              "3.- Insertar Ventas\n" +
	      		      "4.- Consultar Ventas de un Ciente\n" +
	 		      "5.- Salir");
           
            option = scanner.nextLine();            
           
            switch(option){
                case "1":
                    //llamar a ver clientes
                    break;
                case "2":
                    //llamar a ver productos
                    break;
                case "3":
                    //llamar a insertar ventas
                    break;
                case "4":
                    //llamar a consultar ventas de un cliente
                    break;
                case "5":
                    //salir de la aplicación
                    System.out.println("Gracias por usar la aplicación!!");
                    break;
                default:
                    System.out.println("Opción no valida.");
                    break;
            }
       } while(!option.equals("5"));
    }

}
