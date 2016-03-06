package models;

public class ProductSale {
    
    //Class variables
    private int idProduct;
    private int idSale;
    private int quantity;
    
    //Getters
    public int getIdProduct(){
        return this.idProduct;
    }
    
    public int getIdSale(){
        return this.idSale;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    //Setters
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
