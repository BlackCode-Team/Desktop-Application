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

    public void ajouterUtilisateur(Utilisateur u);

    public void modifierUtilisateur(int id, Utilisateur u);

    public void supprimerUtilisateur(Utilisateur u);

    public List<Utilisateur> afficherUtilisateur();

    public List<Utilisateur> Selectionparid(int id);

}
