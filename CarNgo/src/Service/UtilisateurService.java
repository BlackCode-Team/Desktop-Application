/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Utilisateur;
import java.util.List;
import java.sql.*;
import BD.MyConnection;
import Entity.TypeUser;
import java.util.ArrayList;
/**
 *
 * @author Jokser
 * 
 * 
 */


public class UtilisateurService implements UtilisateurInterface {
    
    Connection cnx;
    public UtilisateurService() {
         cnx = MyConnection.getInstance().getCnx();
    }
    
   
    @Override
    public void ajouterUtilisateur(Utilisateur u) {
         try {
            String sql = "INSERT INTO `utilisateur`(`nom`, `prenom`, `pwd`, `email`, `cin`, `permis`) VALUES ('"+u.getRole()+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getPassword()+"','"+u.getEmail()+"','"+u.getCin()+"','"+u.getPermis()+"')";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, u.getIdUser());
            
            ste.setString(3, u.getNom());
            ste.setString(4, u.getPrenom());
            ste.setString(5, u.getPassword());
            ste.setString(6, u.getEmail());
            ste.setString(7, u.getCin());
            ste.setString(8, u.getPermis());
            ste.setInt(9, u.getIdAffectationBadge());
            
            ste.executeUpdate();
            System.out.println("----Utilisateur Ajouté----");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierUtilisateur(int id, Utilisateur u) {
       String sq2 = "UPDATE `utilisateur` SET `nom`='"+u.getNom()+"',`prenom`='"+u.getPrenom()+"',`pwd`='"+u.getPassword()+"',`email`='"+u.getEmail()+"',`cin`='"+u.getCin()+"',`permis`='"+u.getPermis()+"' WHERE 1";
        try {
            PreparedStatement ste = cnx.prepareStatement(sq2);
            ste.setString(3, u.getNom());
            ste.setString(4, u.getPrenom());
            ste.setString(5, u.getPassword());
            ste.setString(6, u.getEmail());
            ste.setString(7, u.getCin());
            ste.setString(8, u.getPermis());
            ste.setInt(9, u.getIdAffectationBadge());
            ste.executeUpdate();
            System.out.println("------ Utilisateur Modifié !!------");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerUtilisateur(Utilisateur u) {
         String sq3 = "DELETE FROM `utilisateur` WHERE `iduser`='"+u.getIdUser()+"'";
        try {
            PreparedStatement ste = cnx.prepareStatement(sq3);
            ste.setInt(1, u.getIdUser());
            ste.executeUpdate();
            System.out.println("----- Utilisateur supprimé -----");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     
    }

    @Override
    public List<Utilisateur> afficherUtilisateur() {
        
      List<Utilisateur> users = new ArrayList<>();
        try {
            String sq4 = "SELECT * FROM `utilisateur`";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sq4);
            while (s.next()) {

                Utilisateur u = new Utilisateur(1, "Chagtmi", "Wissem", "****", "w.chagtmi@gmail.com", "urlCIN", "urlPermis", 1, TypeUser.agent);
                users.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public List<Utilisateur> Selectionparid(int id) {
       List<Utilisateur> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `utilisateur` WHERE`iduser`='?'";
           PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Utilisateur u = new Utilisateur(1, "Chagtmi", "Wissem", "****", "w.chagtmi@gmail.com", "urlCIN", "urlPermis", 1, TypeUser.agent);
                users.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
}
   

   
    

