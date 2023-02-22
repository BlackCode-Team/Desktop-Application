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
    public int ajouterUtilisateur(Utilisateur u) {
        int id = -1;
        try {
            String req1 = "INSERT INTO `utilisateur`(`role`, `nom`, `prenom`, `pwd`, `email`, `cin`, `permis`, `nbpoint`) "
                    + "VALUES ('" + u.getRole() + "','" + u.getNom() + "','" + u.getPrenom() + "','" + u.getPassword() + "','" + u.getEmail() + "','" + u.getCin() + "','" + u.getPermis() + "','" + u.getNbPoint() + "') WHERE "+u.getEmail()+"LIKE '%_@__%.__%";

            Statement ste = cnx.createStatement();
            id = ste.executeUpdate(req1);
            System.out.println("----Utilisateur Ajouté----");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }

    @Override
    public void modifierUtilisateur(Utilisateur u) {
        //String sq2 = "UPDATE `utilisateur` SET `nom`='" + u.getNom() + "',`prenom`='" + u.getPrenom() + "',`pwd`='" + u.getPassword() + "',`email`='" + u.getEmail() + "',`cin`='" + u.getCin() + "',`permis`='" + u.getPermis() + "' WHERE 1";
        String sq = "UPDATE `utilisateur` SET `nom`=?,`prenom`=?,`pwd`=?,`email`=?,`cin`=?,`permis`=?,`nbpoint`=? WHERE 1";
        try {
            PreparedStatement ste = cnx.prepareStatement(sq);
            
            ste.setString(2, u.getNom());
            ste.setString(3, u.getPrenom());
            ste.setString(4, u.getPassword());
            ste.setString(5, u.getEmail());
            ste.setString(6, u.getCin());
            ste.setString(7, u.getPermis());
            ste.setInt(8, u.getNbPoint());

            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
            System.out.println("------ Utilisateur Modifié !------");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean supprimerUtilisateur(Utilisateur u) {

        try {
            String req = "DELETE FROM `utilisateur` WHERE `utilisateur`.`iduser` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, u.getIdUser());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");

                System.out.println("------ Utilisateur supprimé !------");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    @Override
    public List<Utilisateur> afficherUtilisateur() {

        List<Utilisateur> ListeUsers = new ArrayList<>();
        try {
            String sq4 = "SELECT * FROM `utilisateur`";
            Statement ste = cnx.createStatement();
            ResultSet res = ste.executeQuery(sq4);
//            String iduser = res.getString(1);
            while (res.next()) {
                
                Utilisateur u = new Utilisateur();
//                u.setRole(res.getString("Role"));         
                    u.setRole(TypeUser.fromString(res.getString("role")));
                
                if (u.getRole()==TypeUser.client) {
                    u.setNom(res.getString("nom"));
                    u.setPrenom(res.getString("prenom"));
                    u.setCin(res.getString("cin"));
                    u.setEmail(res.getString("email"));
                    u.setPassword(res.getString("pwd"));
                    u.setPermis(res.getString("permis"));
                    u.setNbPoint(res.getInt("nbpoint"));
                    u.setIdUser(res.getInt("iduser"));
                }
                else if (u.getRole()==TypeUser.admin) {
                    u.setIdUser(res.getInt("iduser"));
                    u.setNom(res.getString("nom"));
                    u.setCin(res.getString("cin"));
                    u.setPrenom(res.getString("prenom"));
                    u.setEmail(res.getString("email"));
                }
                else {
                    u.setIdUser(res.getInt("iduser"));
                    u.setCin(res.getString("cin"));
                    u.setNom(res.getString("nom"));
                    u.setPrenom(res.getString("prenom"));
                    u.setEmail(res.getString("email"));
                    
                }
                
                ListeUsers.add(u);
//                System.out.println("\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListeUsers;
    }

   
}
