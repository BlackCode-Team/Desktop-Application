package entities;

import java.util.List;

public class PlaceStationnement {
    private int id;
    private String Location;
    private List<String> matricules;
    public PlaceStationnement(){ }

    public PlaceStationnement(String Location){
        this.Location=Location;
    }

    public PlaceStationnement(String location, List<String> matricules) {
        Location = location;
        this.matricules = matricules;
    }

    public int getId() {return id;}
    public void setId(int id){this.id=id;}

    public String getLocation() {return Location;}

    public void setLocation(String location) {Location = location;}

    public List<String> getMatricules() {return matricules;}

    public void setMatricules(List<String> matricules) {this.matricules = matricules;}

    @Override
    public String toString() {
        return "PlaceStationnement{" +
                "Location='" + Location + matricules+'\'' +
                '}';
    }
}
