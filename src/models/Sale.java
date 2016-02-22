package models;

import java.sql.Date;

public class Sale {
    
    private int idSale;
    private Date saleDate;
    
    public int getIdSale(){
        return idSale;
    }
    
    public Date getSaleDate(){
        return saleDate;
    }
}
