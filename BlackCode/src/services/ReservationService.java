package services;


import entities.Reservation;
import utils.MyConnection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.EtatReservation;
import entities.Utilisateur;
import entities.Vehicule;


public class ReservationService implements IReservationService {

    Connection myConnex= MyConnection.getInstance().getMyconnex();

    @Override
    public boolean ajouterReservation(Reservation res) {
        boolean succes = false;
        try {
        
            String req1 = "INSERT INTO `reservation`(`idreservation`, `datedebut`,`datefin`,`status`, `idvehicule`, `iduser`, `iditineraire`)"
                    + " VALUES (Null,?,?,?,?,?,?)";

            PreparedStatement ps = myConnex.prepareStatement(req1);
            // if(res.getIdvehicule()==true){
            
            ps.setDate(1, (Date) res.getDatedebut());
            ps.setDate(2, (Date) res.getDatefin());
            ps.setString(3, res.getStatus().toString());
            ps.setInt(4, res.getIdvehicule());
            ps.setInt(5, res.getIduser());
            ps.setInt(6, res.getIditineraire());
            ps.executeUpdate();
            succes = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return succes;
    }

    @Override
    public boolean modifierReservation(int idreservation, Reservation res) {
        boolean succes = false;
        try {
            String req1 = "UPDATE reservation set   iduser=? , datedebut=?, datefin=?, status=? ,idvehicule=? ,iditineraire=?  WHERE idreservation = ?";
            PreparedStatement ste = myConnex.prepareStatement(req1);
            ste.setInt(1, res.getIduser());
            ste.setDate(2, (Date) res.getDatedebut());
            ste.setDate(3, (Date) res.getDatefin());
            ste.setString(4, res.getStatus().toString());
            //status.valueOf(rstset.getString("status"))
            ste.setInt(5, res.getIdvehicule());
            ste.setInt(6, res.getIditineraire());
            ste.setInt(7, idreservation);

            ste.executeUpdate();
            succes = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return succes;

    }

    @Override
    public void supprimerReservation(int idreservation, Reservation res) {
        try {
            String req = "delete from reservation where idreservation= ? ";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, idreservation);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> afficherReservations() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "select * from reservation";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ResultSet resu = ps.executeQuery();
            while (resu.next()) {
                Reservation res = new Reservation();
                res.setIdreservation(resu.getInt("idreservation"));
                res.setDatedebut(resu.getDate("datedebut"));
                res.setDatedebut(resu.getDate("datefin"));
                res.setStatus(EtatReservation.valueOf(resu.getString("status")));
                res.setIdvehicule(resu.getInt("idvehicule"));
                res.setIduser(resu.getInt("iduser"));
                res.setIditineraire(resu.getInt("iditineraire"));
                list.add(res);
            }
        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

        }
        return list;
    }

    public ObservableList<Reservation> afficherReservations22() {
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        try {
            String req = "select * from reservation";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ResultSet resu = ps.executeQuery();
            while (resu.next()) {
                Reservation res = new Reservation();
                res.setIdreservation(resu.getInt("idreservation"));
                res.setDatedebut(resu.getDate("datedebut"));
                res.setDatefin(resu.getDate("datefin"));
                res.setStatus(EtatReservation.valueOf(resu.getString("status")));
                res.setIdvehicule(resu.getInt("idvehicule"));
                res.setIduser(resu.getInt("iduser"));
                res.setIditineraire(resu.getInt("iditineraire"));
                list.add(res);
            }
        } catch (SQLException ex) {            System.out.println(ex.getMessage());

        }
        return list;
    }
    public ObservableList<Reservation> afficherReservations23() {
    ObservableList<Reservation> list = FXCollections.observableArrayList();
    try {
        String req = "SELECT r.*, u.cin, v.matricule, i.pointdepart, i.pointarrivee " +
                     "FROM reservation r " +
                     "JOIN utilisateur u ON u.iduser = r.iduser " +
                     "JOIN vehicule v ON v.idvehicule = r.idvehicule " +
                     "JOIN itineraire i ON i.iditineraire = r.iditineraire";

        PreparedStatement ps = myConnex.prepareStatement(req);
        ResultSet resu = ps.executeQuery();
        while (resu.next()) {
            Reservation res = new Reservation();
            res.setIdreservation(resu.getInt("idreservation"));
            res.setDatedebut(resu.getDate("datedebut"));
            res.setDatefin(resu.getDate("datefin"));
            res.setStatus(EtatReservation.valueOf(resu.getString("status")));
            res.setIdvehicule(resu.getInt("idvehicule"));
            res.setIduser(resu.getInt("iduser"));
            res.setIditineraire(resu.getInt("iditineraire"));
            res.setCin(resu.getString("cin"));
            res.setMatricule(resu.getString("matricule"));
            res.setPointdepart(resu.getString("pointdepart"));
            res.setPointarrivee(resu.getString("pointarrivee"));
            list.add(res);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}

//
//    public ObservableList<Reservation> afficherReservations23() {
//        ObservableList<Reservation> list = FXCollections.observableArrayList();
//        try {
//String req = "SELECT * FROM reservation r ";
//
//            PreparedStatement ps = myConnex.prepareStatement(req);
//            ResultSet resu = ps.executeQuery();
//            while (resu.next()) {
//                Reservation res = new Reservation();
//                res.setIdreservation(resu.getInt("idreservation"));
//                res.setDatedebut(resu.getDate("datedebut"));
//                res.setDatefin(resu.getDate("datefin"));
//                res.setStatus(EtatReservation.valueOf(resu.getString("status")));
//                res.setIdvehicule(resu.getInt("idvehicule"));
//                res.setIduser(resu.getInt("iduser"));
//                res.setIditineraire(resu.getInt("iditineraire"));
//                list.add(res);}
////                String req2 = "SELECT * FROM reservation WHERE iduser = ?";
////                PreparedStatement ps2 = myConnex.prepareStatement(req2);
////                ps2.setInt(1, res.getIduser());
////                ResultSet resu2 = ps2.executeQuery();
////                if (resu2.next()) {
////                    int iduser = resu2.getInt("iduser");
////                    Utilisateur user = new Utilisateur();
////                    String cin = user.getCinById(iduser);
////                    res.getUtilisateur().setCin(cin);
////                }
//                String req2 = "SELECT u.cin FROM utilisateur u JOIN reservation r ON u.iduser = r.iduser ";
//                PreparedStatement ps2 = myConnex.prepareStatement(req2);
//                ResultSet resu2 = ps2.executeQuery();
//                while (resu2.next()) {
//                    String cin = resu2.getString("cin");
//                    res.setCin(cin);
//                }
//
//                String req3 = "SELECT u.matricule FROM vehicule u JOIN reservation r ON u.idvehicule = r.idvehicule ";
//                PreparedStatement ps3 = myConnex.prepareStatement(req3);
//                ResultSet resu3 = ps3.executeQuery();
//                if (resu3.next()) {
//                    String matricule = resu3.getString("matricule");
//                    res.setMatricule(matricule);
//                                    }
//
//                String req4 = "SELECT u.pointdepart FROM itineraire u JOIN reservation r ON u.iditineraire = r.iditineraire ";
//                PreparedStatement ps4 = myConnex.prepareStatement(req4);
//                ResultSet resu4 = ps4.executeQuery();
//                if (resu4.next()) {
//                    String pointDepart = resu4.getString("pointdepart");
//                    res.setPointdepart(pointDepart);
//                }
//                String req5 = "SELECT u.pointarrivee FROM itineraire u JOIN reservation r ON u.iditineraire = r.iditineraire ";
//                PreparedStatement ps5 = myConnex.prepareStatement(req5);
//                ResultSet resu5 = ps5.executeQuery();
//                if (resu5.next()) {
//                    String pointArrivee = resu5.getString("pointarrivee");
//                    res.setPointarrivee(pointArrivee);
//                }
//
//             
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return list;}
////    }
//
//    public String getMatriculeById(int id) {
//        String matricule = null;
//        try {
//            String req = "select matricule from vehicule where idvehicule=?";
//            PreparedStatement ps = myConnex.prepareStatement(req);
//
//            ps.setInt(1, id);
//            ResultSet resu = ps.executeQuery();
//            if (resu.next()) {
//                matricule = resu.getString("matricule");
//            }
//            System.out.println(matricule);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return matricule;
//    }
//   public int getVehiculeIdByMatricule(String matricule){
//        int id = 0;
//        try {
//            String req = "select idvehicule from vehicule where matricule=?";
//            PreparedStatement ps = myConnex.prepareStatement(req);
//
//            ps.setString(1, matricule);
//            ResultSet resu = ps.executeQuery();
//            if (resu.next()) {
//                id = resu.getInt("idvehicule");
//            }
//            System.out.println(id);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return id;
//    }
//
//    public String getStatusById(int id) {
//        String status = null;
//        try {
//            String req = "select status from reservation where idreservation=?";
//            PreparedStatement ps = myConnex.prepareStatement(req);
//
//            ps.setInt(1, id);
//            ResultSet resu = ps.executeQuery();
//            if (resu.next()) {
//                status = resu.getString("status");
//            }
//            System.out.println(status);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return status;
//    }
//
//    public List<String> getMatricule()  {
//        List<String> mat=new ArrayList<>();
//    try {
//        String req = "select matricule from vehicule ";
//        PreparedStatement ps = myConnex.prepareStatement(req);
//        ResultSet resu = ps.executeQuery();
//        while (resu.next()) {
//            mat.add(resu.getString("matricule"));
//            System.out.println(mat); 
//
//        }         
//    }
//    catch(SQLException e ){
//                    System.out.println(e.getMessage());
//
//        
//    }
//        return mat;
//
//    }
}