package models;

public class ProductSale {
    
    private int idProduct;
    private int idSale;
    private int quantity;
    
    public int getIdProduct(){
        return this.idProduct;
    }
    
    public int getIdSale(){
        return this.idSale;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
