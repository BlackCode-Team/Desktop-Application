/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.carngo.entities;

/**
 *
 * @author mhcab
 */
public class itineraire {
    int idUser,idItineraire;
    String pointDepart;
    String pointDestination;
    float kilometrage;
    int DureéEstime;


    public float getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(float kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(int idItineraire) {
        this.idItineraire = idItineraire;
    }

    public itineraire(int idUser, int idItineraire, String pointDepart, String pointDestination, float kilometrage, int DureéEstime) {
        this.idUser = idUser;
        this.idItineraire = idItineraire;
        this.pointDepart = pointDepart;
        this.pointDestination = pointDestination;
        this.kilometrage = kilometrage;
        this.DureéEstime = DureéEstime;
    }


    public itineraire(int idUser,String pointDepart, String pointDestination, int DureéEstime) {
        this.idUser = idUser;
        this.pointDepart = pointDepart;
        this.pointDestination = pointDestination;
        this.DureéEstime = DureéEstime;
    }

    public itineraire() {
    }

    public itineraire(String pointDepart, String pointDestination, int DureéEstime) {
        this.pointDepart = pointDepart;
        this.pointDestination = pointDestination;
        this.DureéEstime = DureéEstime;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public String getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public String getPointDestination() {
        return pointDestination;
    }

    public void setPointDestination(String pointDestination) {
        this.pointDestination = pointDestination;
    }

    public int getDureéEstime() {
        return DureéEstime;
    }

    @Override
    public String toString() {
        return "itineraire{ pointDepart=" + pointDepart + ", pointDestination=" + pointDestination + ", DureeEstime=" + DureéEstime +", kilometrage=" + kilometrage + '}';
    }

    public void setDureéEstime(int DureéEstime) {
        this.DureéEstime = DureéEstime;
    }
    
}
