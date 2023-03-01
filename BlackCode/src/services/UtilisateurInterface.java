package services;


import entities.Utilisateur;
import java.util.List;

/**
 *
 * @author Jokser
 */
public interface UtilisateurInterface {

    public int ajouterUtilisateur(Utilisateur u);

    public void modifierUtilisateur(String cin, Utilisateur u);
    
    public boolean supprimerUtilisateur(Utilisateur u);

    public List<Utilisateur> afficherUtilisateur();
    
    public Utilisateur afficherUtilisateurParCin(String cin);

    
}
