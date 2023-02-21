package services;

import entities.Reclamation;


import utils.MyConnection;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class ReclamationService implements IReclamationService {

    Connection myconnex= MyConnection.getInstance().getMyconnex();

    @Override
    public boolean ajouterReclamation(Reclamation r) {
        try {
            String req1 = "INSERT INTO `reclamation`(`contenu`, `date`) VALUES (?, ?)";
            PreparedStatement ps = myconnex.prepareStatement(req1);
            ps.setString(1, r.getContenu());
            ps.setDate(2, new java.sql.Date(r.getDate().getTime()));
            ps.executeUpdate();
            System.out.println("Réclamation crée");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modifierReclamation(Reclamation r) {
        String req =  "update reclamation r join utilisateur u on (r.iduser=r.iduser)" +
                " set `contenu`=?, `date`=? WHERE idreclamation=?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, r.getContenu());
            ps.setDate(2, new java.sql.Date(r.getDate().getTime()));
            ps.setInt(3, r.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean supprimerReclamation(Reclamation r) {
        String req = "DELETE FROM reclamation r join utilisateur u on (r.idutilisateur=r.idutilisateur) WHERE idreclamation=?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setInt(1, r.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Reclamation> afficherReclamation() {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "SELECT contenu,date FROM reclamation";
        try {
            Statement ste = myconnex.createStatement();
            ResultSet rset = ste.executeQuery(req);
            while (rset.next()) {
                Reclamation r = new Reclamation(rset.getString(1),rset.getDate(2));
                reclamations.add(r);
            }
            System.out.println("Nombre de réclamations récupérées : " + reclamations.size());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des réclamations : " + e.getMessage());
        }

        return reclamations;
    }


    public List<Reclamation> afficherReclamationParNomUtilisateur(int iduser) {
        List<Reclamation> listreclamation = new ArrayList<>();
        try {
            String req = "SELECT * FROM reclamation WHERE iduser=?";
            PreparedStatement prp = myconnex.prepareStatement(req);
            prp.setInt(1, iduser);
            ResultSet res = prp.executeQuery();
            while (res.next()) {
                Reclamation r = new Reclamation();
                r.setContenu(res.getString("contenu"));
                r.setDate(res.getDate("date"));
                listreclamation.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            String nameuserReq = "SELECT nom FROM utilisateur WHERE iduser=?";
            PreparedStatement prpNameUser = myconnex.prepareStatement(nameuserReq);
            prpNameUser.setInt(1, iduser);
            ResultSet resNameUser = prpNameUser.executeQuery();
            if (resNameUser.next()) {
                String nameuser = resNameUser.getString("nom");
                System.out.println("Réclamations correspondantes à " + nameuser + " : " + listreclamation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listreclamation;
    }
}
