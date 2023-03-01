package services;

import entities.Reservation;
import java.util.List;


public interface IReservationService {
      public boolean ajouterReservation(Reservation res);

    public boolean modifierReservation(int idreservation,Reservation res);

    public void supprimerReservation(int idreservation,Reservation res);

    public List<Reservation> afficherReservations();
}
