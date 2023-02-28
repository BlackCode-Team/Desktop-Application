package services;

import entities.itineraire;
import java.util.List;


public interface InterfaceItineraire {

    public void ajouterItineraire(itineraire i);

    public void modifierItineraire(int id ,itineraire i);

    public void supprimerItineraire(itineraire i);

    public List<itineraire> afficherItineraire();

    public List<itineraire> Selectionparuser(int id);

}
