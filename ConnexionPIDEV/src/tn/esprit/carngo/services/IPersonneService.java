/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tn.esprit.carngo.services;

import tn.esprit.carngo.entities.Personne;
import java.util.List;

/**
 *
 * @author ychaa
 */
public interface IPersonneService {

    public int ajouterPersonne(Personne p);

    public boolean modifierPersonne(int id,Personne p);

    public boolean supprimerPersonne(Personne p);

    public List<Personne> afficherPersonnes();

}
