package dao;		
 		
import exceptions.DAOException;		
import java.sql.Connection;		
import java.util.ArrayList;		
import models.Product;		

public class ProductDAOImplem implements InterfaceDAOGeneral<Product, Integer>{		

    //Inherited method from InterfaceDAO		
    @Override		
    public ArrayList<Product> list(Connection connection) throws DAOException {		
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.		
    }		

}
