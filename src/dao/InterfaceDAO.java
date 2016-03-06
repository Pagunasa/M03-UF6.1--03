package dao;

import exceptions.DAOException;
import java.sql.Connection;
import java.util.ArrayList;

public interface InterfaceDAO<T, PK> {
    
    public ArrayList<T> list(Connection connection) throws DAOException;
}
