package entities;

import java.util.stream.Stream;

public enum StatusVehicule {
    disponible(0),
    réservé(1);

    public int val;
    StatusVehicule(int val) {
        this.val=val;
    }

    public static StatusVehicule fromValue(int value) throws Exception {
        return Stream.of(StatusVehicule.values())
                .filter(s -> s.val == value)
                .findFirst()
                .orElseThrow(()-> new Exception("La valeur est différente de  0 et 1"));
    }
}
