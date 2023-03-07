package services;

import entities.Badge;
import java.util.List;

public interface Ibadge {
    public int ajouterBadge (Badge B);
    public void modifierBadge(int idbadge,Badge B);
    public void supprimerBadge(int idbadge);
    public List<Badge> afficherBadge();
}
