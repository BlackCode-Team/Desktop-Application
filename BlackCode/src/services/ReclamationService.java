package services;

import entities.Reclamation;

import java.sql.Date;

import entities.Vehicule;
import utils.MyConnection;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class ReclamationService implements Iservice <Reclamation>{

    Connection myconnex= MyConnection.getInstance().getMyconnex();

    @Override
    public boolean ajouterentite(Reclamation r) {
        try {
            String req="Select idvehicule from vehicule where matricule=?";
            PreparedStatement p=myconnex.prepareStatement(req);
            p.setString(1, r.getMatricule());
            ResultSet rs= p.executeQuery();
            if (rs.next()) {
                String req1 = "INSERT INTO `reclamation`(`contenu`, `date`,`idvehicule`) VALUES (?, ?,?)";
                PreparedStatement ps = myconnex.prepareStatement(req1);
                ps.setString(1, r.getContenu());
                java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
                ps.setDate(2, date);
                ps.setInt(3,rs.getInt("idvehicule"));
                ps.executeUpdate();
                System.out.println("Réclamation crée");
                return true;
            } else {
                System.out.println("Aucun résultat trouvé");
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean repondreReclamation(Reclamation r) {
        try {
            String req = "UPDATE reclamation SET reponse=? WHERE idreclamation=?";
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, r.getReponse());
            ps.setInt(2, r.getId());
            ps.executeUpdate();
            System.out.println("Réponse ajoutée à la réclamation " + r.getId());
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean supprimerentite(Reclamation r) {
        String req = "DELETE FROM reclamation r WHERE idreclamation=?";
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
    public List<Reclamation> afficherentite() {
        List<Reclamation> reclamations = new ArrayList<>();
        /*String req = "SELECT r.contenu, r.date, u.nom, v.matricule " +
                "FROM reclamation r " +
                "JOIN utilisateur u ON r.iduser = u.iduser " +
                "JOIN vehicule v ON r.idvehicule = v.idvehicule";*/
        String req = "SELECT r.contenu, r.date, v.matricule " +
                "FROM reclamation r " +
                "JOIN vehicule v ON r.idvehicule = v.idvehicule";
        try {
            Statement ste = myconnex.createStatement();
            ResultSet rset = ste.executeQuery(req);
            while (rset.next()) {
                Reclamation r = new Reclamation(rset.getString("contenu"), rset.getDate("date"));
                Vehicule v=new Vehicule();
                //Utilisateur u=new Utilisateur();
                //u.setCin(rset.getInt("cin"));
                //r.setCin(u.getCin());
                v.setMatricule(rset.getString("matricule"));
                r.setMatricule(v.getMatricule());
                reclamations.add(r);
            }
            System.out.println("Nombre de réclamations récupérées : " + reclamations.size());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des réclamations : " + e.getMessage());
        }
        return reclamations;
    }

    public List<Reclamation> afficherReclamationParUser(int iduser) {
        List<Reclamation> listreclamations= new ArrayList<>();
        try {
            String req = "SELECT r.contenu, r.date, v.matricule FROM reclamation r " +
                    "join vehicule v ON r.idvehicule = v.idvehicule  where iduser=?";
            PreparedStatement prp = myconnex.prepareStatement(req);
            prp.setInt(1, iduser);
            ResultSet res = prp.executeQuery();
            while (res.next()) {
                Reclamation r = new Reclamation();
                r.setContenu(res.getString("contenu"));
                r.setDate(res.getDate("date"));
                Vehicule v = new Vehicule();
                v.setMatricule(res.getString("matricule"));
                r.setMatricule(v.getMatricule());
                listreclamations.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            String userReq = "SELECT cin FROM utilisateur WHERE iduser=?";
            PreparedStatement prpNameUser = myconnex.prepareStatement(userReq);
            prpNameUser.setInt(1, iduser);
            ResultSet resCinUser = prpNameUser.executeQuery();
            if (resCinUser.next()) {
                String cinuser = resCinUser.getString("cin");
                //System.out.println("Réclamations correspondantes à " + cinuser + " : " + listreclamations);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listreclamations;
    }

    /*@Override
    public boolean modifierentite(Reclamation r) {
        String req =  "update reclamation r set `contenu`=?, `date`=? WHERE idreclamation=?";
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
    }*/
}
