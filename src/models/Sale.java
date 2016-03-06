package models;

import java.sql.Date;

public class Sale {
    
    private int idSale;
    private Date saleDate;
    private String clientCif;
    
    public int getIdSale(){
        return this.idSale;
    }
    
    public Date getSaleDate(){
        return this.saleDate;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
    
    public String getClientCif(){
        return this.clientCif;
    }
    
    public void setClientCif(String clientCif){
        this.clientCif = clientCif;
    }
}
