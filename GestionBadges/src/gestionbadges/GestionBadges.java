/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbadges;

import entities.Badge;
import services.badgeService;

public class GestionBadges {

    public static void main(String[] args) {
        Badge B = new Badge();
        Badge B1 = new Badge("gold",355,1);
        badgeService badgeService = new badgeService();
       System.out.println(badgeService.ajouterBadge(B1));
     //badgeService.modifierBadge(3, B1);
    // badgeService.supprimerBadge(4);
     //badgeService.supprimerBadge2(B1);
        System.out.println(badgeService.afficherBadge());
     

    }
    
}
