package models;

public class Product {
    
    private int product;
    private String description;
    private int currenStock;
    private double pvp;

    public int getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public int getCurrenStock() {
        return currenStock;
    }

    public double getPvp() {
        return pvp;
    }

    public void setCurrenStock(int currenStock) {
        this.currenStock = currenStock;
    }  
}
