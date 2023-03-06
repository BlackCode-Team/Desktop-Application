package entities;

import java.sql.Date;
import java.util.List;


public class Reservation {

    private int idreservation;
    private Date datedebut;
    private Date datefin;
    private int idvehicule;
    private int iduser;
    private int iditineraire;
    private String pointdepart;
    private String pointarrivee;
    public  Utilisateur utilisateur;
    private EtatReservation status;
    private String cin;
    private String matricule;

    
    



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
    public Reservation(int iduser, Date datedebut, Date datefin,int idvehicule, int iditineraire) {
        this.iduser = iduser;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.idvehicule = idvehicule;
        this.iditineraire = iditineraire;

    }

    public Reservation(int idreservation, Date datedebut, Date datefin, int idvehicule, int iduser, int iditineraire, EtatReservation status) {
        this.idreservation = idreservation;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.idvehicule = idvehicule;
        this.iduser = iduser;
        this.iditineraire = iditineraire;
        this.status = status;
    }

    public Reservation(Date datedebut, Date datefin, int idvehicule, int iduser, int iditineraire, EtatReservation status) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.idvehicule = idvehicule;
        this.iduser = iduser;
        this.iditineraire = iditineraire;
        this.status = status;
    }
  

    public Reservation(Date datedebut) {
        this.datedebut = datedebut;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public Reservation(int idvehicule) {
        this.idvehicule = idvehicule;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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

    public String getPointdepart() {
        return pointdepart;
    }

    public void setPointdepart(String pointdepart) {
        this.pointdepart = pointdepart;
    }

    public String getPointarrivee() {
        return pointarrivee;
    }
    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public EtatReservation getStatus() {
        return status;
    }

    public void setStatus(EtatReservation status) {
        this.status = status;
    }
    public void setPointarrivee(String pointarrivee) {
        this.pointarrivee = pointarrivee;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
// public void afficherModeleVehicule() {
//        // Rechercher le véhicule correspondant à l'ID de la réservation
//        for (Vehicule vehicule : vehicules) {
//            if (vehicule.getIdvehicule()== idvehicule) {
//                System.out.println("Modèle du véhicule : " + vehicule.getModele());
//                return;
//            }
//        }
//        // Si aucun véhicule correspondant n'a été trouvé, afficher un message d'erreur
//        System.out.println("Véhicule non trouvé !");
//    }

    @Override
    public String toString() {
        return "Reservation{" + "idreservation=" + idreservation + ", datedebut=" + datedebut + ", idvehicule=" + idvehicule + ", iduser=" + iduser + ", iditineraire=" + iditineraire + ", utilisateur=" + utilisateur + '}';
    }


}
