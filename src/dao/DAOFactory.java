package dao;

public class DAOFactory {
    
    public ClientDAOImplem createClientDAO(){
        return new ClientDAOImplem();
    }
    
    public ProductDAOImplem createProductDAO(){
        return new ProductDAOImplem();
    }
    
    public SaleDAOImplem createSaleDAO(){
        return new SaleDAOImplem();
    }
}
