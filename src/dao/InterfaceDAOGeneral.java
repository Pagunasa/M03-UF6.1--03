package dao;		
 		
import exceptions.DAOException;		
import java.sql.Connection;		
import java.util.ArrayList;		

public interface InterfaceDAOGeneral<T, PK> {		

    public ArrayList<T> list(Connection connection) throws DAOException;		
}
