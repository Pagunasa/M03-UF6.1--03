package dao;

public class DAOFactory {
    
    //Get a new ClientDAOImplem
    public ClientDAOImplem createClientDAO(){
        return new ClientDAOImplem();
    }
    
    //Get a new ProductDAOImplem
    public ProductDAOImplem createProductDAO(){
        return new ProductDAOImplem();
    }
    
    //Get a new SaleDAOImplem
    public SaleDAOImplem createSaleDAO(){
        return new SaleDAOImplem();
    }
}
