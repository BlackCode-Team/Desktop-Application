package services;

import entities.Offre;
import java.util.List;

public interface Ioffre {
    public int ajouterOffre (Offre O);
    public void modifierOffre(int idoffre,Offre O);
    public void supprimerOffre(int idoffre);
    public List<Offre> afficherOffre();
    
    
    
    
    
    
    
    
    
}
