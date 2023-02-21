/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Utilisateur;
import java.util.List;

/**
 *
 * @author Jokser
 */
public interface UtilisateurInterface {

    public int ajouterUtilisateur(Utilisateur u);

    public void modifierUtilisateur(Utilisateur u);

    public boolean supprimerUtilisateur(Utilisateur u);

    public List<Utilisateur> afficherUtilisateur();

    
}
