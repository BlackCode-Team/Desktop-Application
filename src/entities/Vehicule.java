package entities;

import java.util.Objects;

public class Vehicule {
    protected int id;
    protected TypeVehicule type;
    protected String modele;
    protected int batterie;
    protected int puissance;
    protected String matricule;

    private int maxspeed;
    public Vehicule() {}

    public Vehicule(TypeVehicule type, String modele, int batterie, int puissance, String matricule) {
        this.type = type;
        this.modele = modele;
        this.batterie = batterie;
        this.puissance = puissance;
        this.matricule = matricule;
    }

    public Vehicule(TypeVehicule type, String modele, int batterie, int maxspeed) {
        this.type = type;
        this.modele = modele;
        this.batterie = batterie;
        this.maxspeed = maxspeed;
    }

    public int getId() {return id;}
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

    @Override
    public String toString() {
        if(getType()==TypeVehicule.voiture)
            return "Voiture{" +  "matricule:" + matricule + ", puissance:" + puissance +
                        ", modele:" + modele + ", batterie:" + batterie + '}';
        else
            return "Trottinette{" + "type='" + type + ", modele='" + modele +
                        "maxspeed='" + maxspeed + ", batterie=" + batterie + '}';
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