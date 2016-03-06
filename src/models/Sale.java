package models;

import java.sql.Date;

public class Sale {
    
    //Class variables
    private int idSale;
    private Date saleDate;
    private String clientCif;
    
    //Getters
    public int getIdSale(){
        return this.idSale;
    }
    
    public Date getSaleDate(){
        return this.saleDate;
    }
    
    public String getClientCif(){
        return this.clientCif;
    }
    
    //Setters
    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
    
    public void setClientCif(String clientCif){
        this.clientCif = clientCif;
    }
}
