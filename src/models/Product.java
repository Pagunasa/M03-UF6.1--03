package models;

public class Product {
    
    //Class variables
    private int idProduct;
    private String description;
    private int currenStock;
    private double pvp;

    //Getters
    public int getIdProduct() {
        return this.idProduct;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCurrenStock() {
        return this.currenStock;
    }

    public double getPvp() {
        return this.pvp;
    }

    //Setters
    public void setCurrenStock(int currenStock) {
        this.currenStock = currenStock;
    }  
}
