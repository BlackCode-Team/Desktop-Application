/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import tn.esprit.carngo.entities.itineraire;
import tn.esprit.carngo.service.ItineraireService;

/**
 *
 * @author mhcab
 */
public class main {
       public static void main(String[] args) {
        ItineraireService is = new ItineraireService();
        itineraire i = new itineraire(1,2,"benzart","tunis",30,50);
         itineraire i2 = new itineraire("sousse","tunis",60);
      is.ajouterItineraire(i);
     //is.supprimerItineraire(i);
      //is.modifierItineraire(1, i2 );
        System.out.println(is.afficherItineraire());
        
        
    }
}
