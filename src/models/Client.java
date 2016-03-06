package models;

public class Client {
    
    //Class variables
    private String cif;
    private String name;
    private String direction;
    private String town;
    private String telephone;

    //Getters
    public String getCif() {
        return cif;
    }

    public String getName() {
        return name;
    }

    public String getDirection() {
        return direction;
    }

    public String getTown() {
        return town;
    }

    public String getTelephone() {
        return telephone;
    }

    //Setters
    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
}
