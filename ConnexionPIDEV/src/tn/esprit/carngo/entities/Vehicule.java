/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.carngo.entities;

/**
 *
 * @author ychaa
 */
public class Vehicule {

    private int idvehicule;
    private String type;
    private String modele;
    private int batterie;
    private String matricule;
    private int puissance;
    private int maxspeed;
    private Boolean disponibilite;
    private int iditineraire;

    public Vehicule() {
    }

    public Vehicule(int idvehicule, String type, String modele, int batterie, String matricule, int puissance, int maxspeed, Boolean disponibilite, int iditineraire) {
        this.idvehicule = idvehicule;
        this.type = type;
        this.modele = modele;
        this.batterie = batterie;
        this.matricule = matricule;
        this.puissance = puissance;
        this.maxspeed = maxspeed;
        this.disponibilite = disponibilite;
        this.iditineraire = iditineraire;
    }

    public int getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(int idvehicule) {
        this.idvehicule = idvehicule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getBatterie() {
        return batterie;
    }

    public void setBatterie(int batterie) {
        this.batterie = batterie;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(int maxspeed) {
        this.maxspeed = maxspeed;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getIditineraire() {
        return iditineraire;
    }

    public void setIditineraire(int iditineraire) {
        this.iditineraire = iditineraire;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "idvehicule=" + idvehicule + ", type=" + type + ", modele=" + modele + ", batterie=" + batterie + ", matricule=" + matricule + ", puissance=" + puissance + ", maxspeed=" + maxspeed + ", disponibilite=" + disponibilite + ", iditineraire=" + iditineraire + '}';
    }
    
    
}
