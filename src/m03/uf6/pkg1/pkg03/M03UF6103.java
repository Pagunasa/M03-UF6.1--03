package m03.uf6.pkg1.pkg03;

public class M03UF6103 {

    public static void main(String[] args) {
        
       int option = 0;
       
       do{
            switch(option){
                case 1:
                    //llamar a ver clientes
                    break;
                case 2:
                    //llamar a ver productos
                    break;
                case 3:
                    //llamar a insertar ventas
                    break;
                case 4:
                    //llamar a consultar ventas de un cliente
                    break;
                case 5:
                    //salir de la aplicación
                    System.out.println("Gracias por usar la aplicación!!");
                    break;
                default:
                    System.out.println("Opción no valida.");
                    break;
            }
       } while(option != 5);
    }

}
