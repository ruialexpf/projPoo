package proj;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private String identifier;
    private String location;

    public Vehicle(String identifier, String location){
        this.identifier = identifier;
        this.location = location;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdentifier() {
        return identifier;
    }
    
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "id: " + identifier + ", localizacao: " + location;
    }
}
