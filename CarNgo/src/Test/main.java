/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entity.TypeUser;
import Entity.Utilisateur;
import Service.UtilisateurService;
//import Entity.itineraire;
//import Service.ItineraireService;

/**
 *
 * @author mhcab
 */
public class main {
       public static void main(String[] args) {
//        ItineraireService is = new ItineraireService();
//        itineraire i = new itineraire(1,2,"benzart","tunis",30);
//         itineraire i2 = new itineraire("sousse","tunis",60);
//      // is.ajouterItineraire(i);
//     //is.supprimerItineraire(i);
//      is.modifierItineraire(1, i2 );
//        System.out.println(is.afficherItineraire());
//        
           Utilisateur u1= new Utilisateur("Smati", "Nada", "*****", "nada@gmail.com", "url1", "url2", 5, TypeUser.admin);
           Utilisateur u3= new Utilisateur("Kefi", "Israa", "*****", "israa@gmail.com", "url1", "url2", 6, TypeUser.client);
           Utilisateur u4= new Utilisateur("Azzouz", "Nesryne", "*****", "nesryne@gmail.com", "url1", "url2", 6, TypeUser.client);
           
           UtilisateurService us1 = new UtilisateurService();
           
//           us1.ajouterUtilisateur(u1);
//           us1.ajouterUtilisateur(u2);
             //us1.ajouterUtilisateur(u4);
//             us1.supprimerUtilisateur(u4);
//             us1.modifierUtilisateur(u1);
//              us1.ajouterUtilisateur(u3);
//              us1.ajouterUtilisateur(u4);
             System.out.println(us1.afficherUtilisateur());

            
           
           
           
    }
}
