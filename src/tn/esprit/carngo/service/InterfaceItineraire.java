/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.carngo.service;

import tn.esprit.carngo.entities.itineraire;
import java.util.List;

/**
 *
 * @author mhcab
 */
public interface InterfaceItineraire {

    public void ajouterItineraire(itineraire i);

    public void modifierItineraire(int id ,itineraire i);

    public void supprimerItineraire(itineraire i);

    public List<itineraire> afficherItineraire();

    public List<itineraire> Selectionparuser(int id);

}
