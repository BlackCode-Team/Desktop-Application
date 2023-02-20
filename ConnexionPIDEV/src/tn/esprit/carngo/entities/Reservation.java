/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.carngo.entities;

import java.util.Date;




/**
 *
 * @author ychaa
 */
public class Reservation {
    private int idreservation;
    private Date datedebut;
    private int idvehicule ; 
    private int iduser;
    private int iditineraire;

    public Reservation() {
    }

    public Reservation(int idreservation, int iduser, Date datedebut, int idvehicule, int iditineraire) {
        this.idreservation = idreservation;
        this.iduser = iduser;
        this.datedebut = datedebut;
        this.idvehicule = idvehicule;
        this.iditineraire = iditineraire;
    }

    public Reservation(int iduser, Date datedebut, int idvehicule, int iditineraire) {
        this.iduser = iduser;
        this.datedebut = datedebut;
        this.idvehicule = idvehicule;
        this.iditineraire = iditineraire;
    }

    public Reservation(Date datedebut) {
        this.datedebut = datedebut;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public int getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(int idvehicule) {
        this.idvehicule = idvehicule;
    }

    public int getIditineraire() {
        return iditineraire;
    }

    public void setIditineraire(int iditineraire) {
        this.iditineraire = iditineraire;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idreservation=" + idreservation + ", datedebut=" + datedebut + ", idvehicule=" + idvehicule + ", iduser=" + iduser + ", iditineraire=" + iditineraire + '}';
    }
    
}
