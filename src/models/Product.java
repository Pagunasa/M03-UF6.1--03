package models;

public class Product {
    
    private int product;
    private String description;
    private int currenStock;
    private double pvp;

    public int getProduct() {
        return this.product;
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

    public void setCurrenStock(int currenStock) {
        this.currenStock = currenStock;
    }  
}
