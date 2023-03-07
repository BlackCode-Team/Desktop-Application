package entities;

import java.util.Objects;

public class Vehicule {
    protected int id;
    private String color;
    private String img;
    protected TypeVehicule type;
    protected String modele;
    protected int batterie;
    protected int puissance;
    protected String matricule;
    private String emplacement;
    private int prix;
    private StatusVehicule status;

    private int maxspeed;
    public Vehicule() {}

    public Vehicule(TypeVehicule type, String modele, int batterie, int puissance, String matricule,int prix) {
        this.type = type;
        this.modele = modele;
        this.batterie = batterie;
        this.puissance = puissance;
        this.matricule = matricule;
        this.prix=prix;
    }

    public Vehicule(TypeVehicule type, String modele, String matricule,int batterie, int maxspeed,int prix) {
        this.type = type;
        this.modele = modele;
        this.batterie = batterie;
        this.maxspeed = maxspeed;
        this.matricule=matricule;
        this.prix=prix;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public TypeVehicule getType() {
        return type;
    }
    public void setType(TypeVehicule type) {
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

    public String getEmplacement() {return emplacement;}
    public void setEmplacement(String emplacement) {this.emplacement = emplacement;}
    public StatusVehicule getStatus() {
        return status;
    }
    public void setStatus(StatusVehicule status) {
        this.status = status;
    }
    public int getPrix() {return prix;}
    public void setPrix(int prix) {this.prix = prix;}
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
            return "matricule:" + matricule + ", type:" + TypeVehicule.voiture + ", puissance:" + puissance +
                    ", modele:" + modele + ", batterie:" + batterie + ", prix:" + prix + ", location:" + emplacement + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule v = (Vehicule) o;
        if(getType()==TypeVehicule.voiture){
            return matricule == v.matricule && puissance == v.puissance;}
        else {
            return Objects.equals(maxspeed, v.maxspeed);
        }
    }

}