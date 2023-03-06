package entities;

import java.util.Date;

public class Reclamation {
    private int id;
    private String contenu;
    private Date date;
    private int cin;
    private String matricule,reponse;

    public Reclamation() {}

    public Reclamation(int id, String contenu, Date date) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
    }

    public Reclamation(String contenu, Date date) {
        this.contenu = contenu;
        this.date = date;
    }

    public Reclamation(String contenu, Date date, String matricule) {
        this.contenu = contenu;
        this.date = date;
        this.matricule = matricule;
    }

    public int getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    public String getMatricule(){return matricule;}

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Réclamation ["+ contenu + date + matricule + cin + "]";
    }
}
