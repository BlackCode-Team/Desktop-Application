package entities;

import java.util.Date;

public class Historique {
    private int idhistorique,idreservation;
    private String matricule,location,cin;
    private int prix;
    private Date date;

    private int numreservation;


    public int getIdhistorique() {
        return idhistorique;
    }

    public int getIdreservation() {return idreservation;}

    public String getMatricule() {
        return matricule;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public String getLocation() {
        return location;
    }

    public int getPrix() {
        return prix;
    }

    public Date getDate() {
        return date;
    }

    public String getCin() {return cin;}

    public void setCin(String  cin) {this.cin = cin;}

    public int getNumreservation() {
        return numreservation;
    }

    public void setNumreservation(int numreservation) {
        this.numreservation = numreservation;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Historique{" +
                "idreservation=" + idreservation +
                ", matricule='" + matricule + '\'' +
                ", location='" + location + '\'' +
                ", prix=" + prix +
                ", date=" + date +
                '}';
    }
}
