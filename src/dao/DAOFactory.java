package dao;

public class DAOFactory {
    
    public ClientDAOJDBCImplem createClientDAO(){
        return new ClientDAOJDBCImplem();
    }
    
    public ProductDAOJDBCImplem createProductDAO(){
        return new ProductDAOJDBCImplem();
    }
    
    public SaleDAOJDBCImplem createSaleDAO(){
        return new SaleDAOJDBCImplem();
    }
}
