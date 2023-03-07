package entities;

import java.util.Date;

public class Historique {
    private int idhistorique,idreservation;
    private String matricule,location,cin;
    private int prix;
    private Date datefin,datedebut;


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

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    @Override
    public String toString() {
        return "Historique{" +
                "idreservation=" + idreservation +
                ", matricule='" + matricule + '\'' +
                ", location='" + location + '\'' +
                ", prix=" + prix +
                ", date=" +datefin+ " ,"+ datedebut+
                '}';
    }
}
