/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tn.esprit.carngo.services;

import tn.esprit.carngo.entities.Reservation;
import java.util.List;

/**
 *
 * @author ychaa
 */
public interface IReservationService {
      public int ajouterReservation(Reservation res);

    public boolean modifierReservation(int idreservation,Reservation res);

    public boolean supprimerReservation(int idreservation,Reservation res);

    public List<Reservation> afficherReservations();
}
