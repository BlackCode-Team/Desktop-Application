package entities;

import java.util.Objects;

public class Trottinette extends Vehicule {
    private int maxspeed;
    public Trottinette() { }

    /*public Trottinette(TypeVehicule type, String modele, int batterie, int maxspeed) {
        super(type, modele, batterie);
        this.maxspeed = maxspeed;
    }*/
    public int getMaxspeed() {
        return maxspeed;
    }
    public void setMaxspeed(int maxspeed) {
        this.maxspeed = maxspeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trottinette that = (Trottinette) o;
        return Objects.equals(maxspeed, that.maxspeed);
    }

    @Override
    public String toString() {
        return "Trottinette{" +
                 "type='" + type + ", modele='" + modele +"maxspeed='" + maxspeed +
                ", batterie=" + batterie + '}';
    }
}
