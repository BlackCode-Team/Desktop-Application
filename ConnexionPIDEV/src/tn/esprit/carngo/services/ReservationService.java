/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.carngo.services;

import tn.esprit.carngo.entities.Reservation;
import tn.esprit.carngo.utilis.myConnection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author ychaa
 */
public class ReservationService implements IReservationService {

    Connection myConnex = myConnection.getInstance().getConnection();

    @Override
    public int ajouterReservation(Reservation res) {
        int iduser = 1;
        try {
            String req1 = "INSERT INTO `reservation`(`idreservation`, `datedebut`, `idvehicule`, `iduser`, `iditineraire`)"
                    + " VALUES (Null,?,?,?,?)";
           
            PreparedStatement ps = myConnex.prepareStatement(req1);
          if(res.getIdvehicule()==true){
           ps.setDate(1, (Date) res.getDatedebut());
            ps.setInt(2, res.getIdvehicule()) ;
            ps.setInt(3, res.getIduser());
            ps.setInt(4, res.getIditineraire());
            iduser = ps.executeUpdate();}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return iduser;
    }

    @Override
    public boolean modifierReservation(int idreservation, Reservation res) {
        boolean succes = false;
        try {
            String req1 = "UPDATE reservation set   iduser=? , datedebut=? ,idvehicule=? ,iditineraire=?  WHERE idreservation = ?";
            PreparedStatement ste = myConnex.prepareStatement(req1);
            ste.setInt(1, res.getIduser());
            ste.setDate(2, (Date) res.getDatedebut());
            ste.setInt(3, res.getIdvehicule());
            ste.setInt(4, res.getIditineraire());
            ste.setInt(5, idreservation);

            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                succes = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return succes;
    }

    @Override
    public boolean supprimerReservation(int idreservation, Reservation res) {
        try {
            String req = "delete from reservation where idreservation= ? ";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, idreservation);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
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
                res.setIdvehicule(resu.getInt("idvehicule"));
                res.setIduser(resu.getInt("iduser"));
                res.setIditineraire(resu.getInt("iditineraire"));
                list.add(res);
            }
        } catch (SQLException ex) {
        }
        return list;
    }
}
