package dao;

import exceptions.DAOException;
import java.sql.Connection;
import java.util.ArrayList;
import models.Product;

public class productDAOJDBCImplem implements interfaceDAO<Product, Integer>{

    @Override
    public ArrayList<Product> list(Connection connection) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertSales(String query) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
