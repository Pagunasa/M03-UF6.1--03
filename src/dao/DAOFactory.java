package dao;

import models.Client;
import models.Product;
import models.Sale;

public class DAOFactory {
    
    public static Client createClient(){
        return new Client();
    }
    
    public static Product createProduct(){
        return new Product();
    }
    
    public static Sale createSale(){
        return new Sale();
    }
}
