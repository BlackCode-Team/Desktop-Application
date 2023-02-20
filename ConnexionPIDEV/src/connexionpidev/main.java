package connexionpidev;

/**
 *
 * @author ychaa
 */
import tn.esprit.carngo.entities.Personne;
import tn.esprit.carngo.entities.Reservation;
import java.sql.*;
import tn.esprit.carngo.services.PersonneService;
import tn.esprit.carngo.services.ReservationService;

public class main {

    public static void main(String[] args) {
//        Personne p = new Personne("aziz", 5);
//        PersonneService personneService = new PersonneService();
//        System.out.println( personneService.ajouterPersonne(p));
//        System.out.println(personneService.modifierPersonne(1, p));
//        System.out.println(personneService.supprimerPersonne(p));
//        System.out.println(personneService.afficherPersonnes());
//        

        java.sql.Date date = java.sql.Date.valueOf("2022-04-03");
        Reservation res = new Reservation(3, date, 5, 1);
        ReservationService reservationService = new ReservationService();
        System.out.println(reservationService.ajouterReservation(res));
        System.out.println("");
       // System.out.println(reservationService.modifierReservation(1, res));
       //System.out.println(reservationService.supprimerReservation(5,res));
        // System.out.println(reservationService.afficherReservations());


    }
}
