package gestionbadges;

import entities.Badge;
import services.badgeService;
import entities.Offre;
import services.offreService;

public class GestionBadges {
    

    public static void main(String[] args) {
        Badge B = new Badge();
        Badge B1 = new Badge("gold",300);
        Badge B2 = new Badge("silver",150);
        Badge B3 = new Badge("bronze",100);
        Badge B4 = new Badge("test",1);
        badgeService badgeService = new badgeService();
//       System.out.println(badgeService.ajouterBadge(B1));
//       System.out.println(badgeService.ajouterBadge(B2));
//       System.out.println(badgeService.ajouterBadge(B3));
//       System.out.println(badgeService.ajouterBadge(B4));
       
     //badgeService.modifierBadge(3, B3);
     //badgeServicesupprimerBadge(7);
     //badgeService.supprimerBadge2(B4);
      // System.out.println(badgeService.afficherBadge());
       
//       Offre O =new Offre();
//       Offre O1 = new Offre("bienvenue","pour les nouveau arrivé",3);
//       Offre O2 = new Offre("silverOffer","pour les silvers",2);
//       Offre O3 = new Offre("goldoffer","pour les golders",1);
//       Offre O5 = new Offre("test","test",4);
        offreService offreService = new offreService();
//       
//        System.out.println(offreService.ajouterOffre(O1));
//        System.out.println(offreService.ajouterOffre(O2));
//        System.out.println(offreService.ajouterOffre(O3));
//     System.out.println(offreService.ajouterOffre(O5));


       // System.out.println(offreService.afficherOffre());
       // System.out.println(badgeService.getTypesBadge());
       
       
       System.out.println(badgeService.getIdByTypeBadge("silver"));
       
               
       
       
    }

    
    
}
