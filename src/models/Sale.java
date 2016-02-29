package models;

import java.sql.Date;

public class Sale {
    
    private int idSale;
    private Date saleDate;
    
    public int getIdSale(){
        return this.idSale;
    }
    
    public Date getSaleDate(){
        return this.saleDate;
    }
}
