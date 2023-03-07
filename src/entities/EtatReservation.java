package entities;

import java.util.stream.Stream;

public enum EtatReservation {
 

    en_cours(0),
    termine(1),
    annulee(2);
    

    public int val;
    EtatReservation(int val) {
        this.val=val;
    }

    public static EtatReservation fromValue(int value) throws Exception {
        return Stream.of(EtatReservation.values())
                .filter(s -> s.val == value)
                .findFirst()
                .orElseThrow(()-> new Exception("La valeur est différente de  0 , 1 et 2"));
    }
}