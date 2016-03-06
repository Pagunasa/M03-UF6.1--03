package models;

public class Product {
    
    //Class variables
    private int idProduct;
    private String description;
    private int currentStock;
    private double pvp;

    //Getters
    public int getIdProduct() {
        return this.idProduct;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCurrentStock() {
        return this.currentStock;
    }

    public double getPvp() {
        return this.pvp;
    }

    //Setters
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrentStock(int currenStock) {
        this.currentStock = currenStock;
    } 
    
    public void setPvp(double pvp) {
        this.pvp = pvp;
    }
    
}
