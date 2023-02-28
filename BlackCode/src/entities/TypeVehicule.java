package entities;

import java.util.stream.Stream;

public enum TypeVehicule {
    voiture(0),
    trottinette(1);

    public int val;
    TypeVehicule(int val) {
        this.val=val;
    }

    public static TypeVehicule fromValue(int value) throws Exception {
        return Stream.of(TypeVehicule.values())
                .filter(s -> s.val == value)
                .findFirst()
                .orElseThrow(()-> new Exception("La valeur est différente de  0 et 1"));
    }

    public static TypeVehicule fromString(String text) {
        for (TypeVehicule type : TypeVehicule.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }



}
