package services;

import entities.Historique;
import entities.Reclamation;
import entities.Vehicule;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueService {

        Connection myconnex= MyConnection.getInstance().getMyconnex();


        public boolean ajouterHistorique() {
            String req = "SELECT idreservation FROM reservation WHERE status = 'terminée'";
            try {
                Statement ste = myconnex.createStatement();
                ResultSet rs = ste.executeQuery(req);

                while (rs.next()) {
                    Historique h = new Historique();
                    h.setIdreservation(rs.getInt("idreservation"));

                    // Ajoutez l'historique
                    String reqHistorique = "INSERT INTO historique (idreservation) VALUES ('" + h.getIdreservation() + "');";
                    Statement steHistorique = myconnex.createStatement();
                    steHistorique.executeUpdate(reqHistorique);
                }

                System.out.println("Toutes les réservations terminées ont été ajoutées à l'historique");
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }



        public boolean supprimerentite(Historique h) {
        String req = "DELETE FROM historique  WHERE idhistorique=?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setInt(1, h.getIdhistorique());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

     public List<Historique> afficherentite() {
         List<Historique> historiques = new ArrayList<>();
         String req = "SELECT r.idreservation, r.datedebut, v.prix, i.pointarrivee, v.matricule FROM historique h " +
                 "INNER JOIN reservation r ON h.idreservation = r.idreservation " +
                 "INNER JOIN itineraire i ON r.iditineraire = i.iditineraire " +
                 "INNER JOIN vehicule v ON r.idvehicule = v.idvehicule";

         try {
             Statement ste = myconnex.createStatement();
             ResultSet rset = ste.executeQuery(req);
             while (rset.next()) {
                 Historique h=new Historique();
//                 Reservation r=new Reservation();
//                 r.setDateDebut(rset.getDate("datefin"));
//                 h.setDate(r.getDateDebut());
//                 r.setId(rset.getString("idreservation"));
//                 h.setNumreservation(r.getId());
//                 Vehicule v=new Vehicule();
//                 v.setMatricule(rset.getString("matricule"));
//                 h.setMatricule(v.getMatricule());
//                 v.setPrix(rset.getInt("prix"));
//                 h.setPrix(v.getPrix());
//                 Itineraire i=new Itineraire();
//                 i.setPointArrivee(rset.getString("pointarrivee"));
//                 h.setLocation(i.getPointArrivee());
                 historiques.add(h);
             }
             System.out.println("Nombre de réclamations récupérées : " + historiques.size());
         } catch (SQLException e) {
             System.out.println("Erreur lors de la récupération des réclamations : " + e.getMessage());
         }
         return historiques;
    }

    public List<Historique> afficherentiteAgent() {
        List<Historique> historiques = new ArrayList<>();
        String req = "SELECT r.idreservation, r.datefin, i.pointarrivee, v.matricule, u.cin FROM historique h " +
                "INNER JOIN reservation r ON h.idreservation = r.idreservation " +
                "INNER JOIN utilisateur u ON u.iduser = r.iduser " +
                "INNER JOIN itineraire i ON r.iditineraire = i.iditineraire " +
                "INNER JOIN vehicule v ON r.idvehicule = v.idvehicule";
        try {
            Statement ste = myconnex.createStatement();
            ResultSet rset = ste.executeQuery(req);
            while (rset.next()) {
                Historique h=new Historique();
//                Reservation r=new Reservation();
//                r.setDateFin(rset.getDate("datefin"));
//                h.setDate(r.getDateFin());
                Vehicule v=new Vehicule();
                v.setMatricule(rset.getString("matricule"));
                h.setMatricule(v.getMatricule());
                v.setPrix(rset.getInt("prix"));
                h.setPrix(v.getPrix());
//                Itineraire i=new Itineraire();
//                i.setPointArrivee(rset.getString("pointArrivee"));
//                h.setLocation(i.getPointArrivee());
//                User u=new User();
//                u.setCin(rset.getString("cin"));
//                h.setCin(u.getCin());
                historiques.add(h);
            }
            System.out.println("Nombre de réclamations récupérées : " + historiques.size());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des réclamations : " + e.getMessage());
        }
        return historiques;
    }
}
