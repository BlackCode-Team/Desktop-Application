package entities;

public class Voiture extends Vehicule {
    protected int puissance;
    protected String matricule;
    public Voiture() {}

   /* public Voiture(TypeVehicule type, String modele, int batterie, int puissance, String matricule) {
        super(type, modele, batterie);
        this.puissance = puissance;
        this.matricule = matricule;
    }

    public Voiture(int id, TypeVehicule type, String modele, int batterie, int puissance, String matricule) {
        super(id, type, modele, batterie);
        this.puissance = puissance;
        this.matricule = matricule;
    }*/

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voiture voiture = (Voiture) o;
        return matricule == voiture.matricule && puissance == voiture.puissance;
    }

    @Override
    public String toString() {
        return "Voiture{" +  "matricule:" + matricule + ", puissance:" + puissance +
                ", modele:" + modele + ", batterie:" + batterie + '}';
    }
}