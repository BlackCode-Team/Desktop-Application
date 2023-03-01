package services;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueService {

        Connection myconnex= MyConnection.getInstance().getMyconnex();


        public boolean ajouterHistorique() {
            String req = "SELECT idreservation FROM reservation WHERE status ='terminée'";
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
    public ObservableList<Historique> afficherHistorique() {
        ObservableList<Historique> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT r.idreservation, r.datefin, r.prix, v.matricule, i.pointarrivee "
                    + "FROM reservation r "
                    + "Left JOIN vehicule v ON v.idvehicule = r.idvehicule "
                    + "Left JOIN itineraire i ON i.iditineraire = r.iditineraire "
                    + "Left JOIN historique h ON h.idreservation = r.idreservation";
            PreparedStatement ps = myconnex.prepareStatement(req);
            ResultSet resu = ps.executeQuery();
            while (resu.next()) {
                Historique h = new Historique();
                h.setIdreservation(resu.getInt("idreservation"));
                h.setDate(resu.getDate("datefin"));
                h.setPrix(resu.getInt("prix"));
                h.setMatricule(resu.getString("matricule"));
                h.setLocation(resu.getString("pointarrivee"));
                list.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }


//     public List<Historique> afficherentite() {
//         List<Historique> historiques = new ArrayList<>();
//         String req = "SELECT r.idreservation, r.datedebut, v.prix, i.pointarrivee, v.matricule FROM historique h " +
//                 "INNER JOIN reservation r ON h.idreservation = r.idreservation " +
//                 "INNER JOIN itineraire i ON r.iditineraire = i.iditineraire " +
//                 "INNER JOIN vehicule v ON r.idvehicule = v.idvehicule";
//
//         try {
//             Statement ste = myconnex.createStatement();
//             ResultSet rset = ste.executeQuery(req);
//             while (rset.next()) {
//                 Historique h=new Historique();
//                Reservation r=new Reservation();
//                 r.setDatedebut(rset.getDate("datefin"));
//                 h.setDate(r.getDatedebut());
//                 r.setIdreservation(rset.getInt("idreservation"));
//                 h.setNumreservation(r.getIdreservation());
//                 Vehicule v=new Vehicule();
//                 v.setMatricule(rset.getString("matricule"));
//                 h.setMatricule(v.getMatricule());
//                 v.setPrix(rset.getInt("prix"));
//                 h.setPrix(v.getPrix());
//                 itineraire i=new itineraire();
//                 i.setPointDestination(rset.getString("pointarrivee"));
//                 h.setLocation(i.getPointDestination());
//                 historiques.add(h);
//             }
//             System.out.println("Nombre de réclamations récupérées : " + historiques.size());
//         } catch (SQLException e) {
//             System.out.println("Erreur lors de la récupération des réclamations : " + e.getMessage());
//         }
//         return historiques;
//    }

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
