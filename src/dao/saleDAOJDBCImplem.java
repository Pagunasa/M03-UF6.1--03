package dao;

import exceptions.DAOException;
import java.sql.Connection;
import java.util.ArrayList;
import models.Sale;

public class saleDAOJDBCImplem implements interfaceDAO<Sale, Integer>{

    @Override
    public ArrayList<Sale> list(Connection connection) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertSales(String query) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
