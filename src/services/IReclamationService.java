package services;


import entities.Reclamation;

import java.util.List;

public interface IReclamationService {
    public boolean ajouterReclamation(Reclamation r);
    public boolean modifierReclamation(Reclamation r);
    public boolean supprimerReclamation(Reclamation r);
    public List<Reclamation> afficherReclamation();
}