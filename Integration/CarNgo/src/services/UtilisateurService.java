package services;

import java.util.List;
import java.sql.*;
import utils.MyConnection;
import entities.Utilisateur;
import entities.TypeUser;

import java.util.ArrayList;


public class UtilisateurService implements UtilisateurInterface {

    Connection cnx;

    public UtilisateurService() {

        cnx = MyConnection.getInstance().getMyconnex();
    }
    
    @Override
    public int ajouterUtilisateur(Utilisateur u) {
        int id = -1;
        
        try {
            String req1 = "INSERT INTO `utilisateur`(`nom`, `prenom`, `email`, `cin`) VALUES ('"+u.getNom()+"','"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getCin()+"')";

            PreparedStatement ste = cnx.prepareStatement(req1);
           
//            ste.setString(1, u.getNom());
//            ste.setString(2, u.getPrenom());
//            ste.setString(3, u.getEmail());
//            ste.setString(4, u.getCin());
            id = ste.executeUpdate(req1);
            System.out.println("----Utilisateur Ajouté----");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }
    public void modifierUtilisateur(String cin, Utilisateur u) {
    try {
        String req = "UPDATE utilisateur SET nom=?, prenom=?, email=? WHERE cin=?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, u.getNom());
        pst.setString(2, u.getPrenom());
        pst.setString(3, u.getEmail());
        pst.setString(4, cin);
        pst.executeUpdate();
        System.out.println("Utilisateur modifié avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
//    @Override
//    public void modifierUtilisateur(Utilisateur u) {
//    String query = "UPDATE utilisateur SET nom=?, prenom=?, email=? WHERE cin=?";
//    try {
//        PreparedStatement statement = cnx.prepareStatement(query);
//        statement.setString(1, u.getNom());
//        statement.setString(2, u.getPrenom());
//        statement.setString(3, u.getEmail());
//        statement.setString(4, u.getCin());
//        int rowsUpdated = statement.executeUpdate();
//        if (rowsUpdated > 0) {
//            System.out.println("L'utilisateur a été modifié avec succès !");
//        }
//    } catch (SQLException ex) {
//        System.out.println("Erreur lors de la modification de l'utilisateur: " + ex.getMessage());
//    }
//}

    

    @Override
    public boolean supprimerUtilisateur(Utilisateur u) {

        try {
            String req = "DELETE FROM `utilisateur` WHERE `utilisateur`.`cin` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getCin());
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
        while (res.next()) {
            Utilisateur u = new Utilisateur();
            u.setRole(TypeUser.fromString(res.getString("role")));
            if (u.getRole() == TypeUser.client) {
                u.setNom(res.getString("nom"));
                u.setPrenom(res.getString("prenom"));
                u.setCin(res.getString("cin"));
                u.setEmail(res.getString("email"));
                u.setPassword(res.getString("pwd"));
                u.setPermis(res.getString("permis"));
                u.setNbPoint(res.getInt("nbpoint"));
                u.setIdUser(res.getInt("iduser"));
            } else if (u.getRole() == TypeUser.admin) {
                u.setIdUser(res.getInt("iduser"));
                u.setNom(res.getString("nom"));
                u.setCin(res.getString("cin"));
                u.setPrenom(res.getString("prenom"));
                u.setEmail(res.getString("email"));
            } else {
                u.setIdUser(res.getInt("iduser"));
                u.setCin(res.getString("cin"));
                u.setNom(res.getString("nom"));
                u.setPrenom(res.getString("prenom"));
                u.setEmail(res.getString("email"));
            }
            ListeUsers.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return ListeUsers;
}

    @Override
    public Utilisateur afficherUtilisateurParCin(String cin) {
        Utilisateur utilisateur = null;
    String requete = "SELECT * FROM utilisateur WHERE cin = ?";
    try {
        PreparedStatement statement = cnx.prepareStatement(requete);
        statement.setString(1, cin);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String email = result.getString("email");
            utilisateur = new Utilisateur(nom, prenom, email, cin);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return utilisateur;
    }
    


   
}
