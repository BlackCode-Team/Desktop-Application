/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.itineraire;
import java.util.List;
import java.sql.*;
import utils.MyConnection;
import java.time.Clock;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItineraireService implements InterfaceItineraire{
  
    public static int a=1;
    Connection cnx;

    public ItineraireService() {
        cnx = MyConnection.getInstance().getMyconnex();
    }

    @Override
    public void ajouterItineraire(itineraire i) {
         try {
            String sql = "insert into itineraire (iduser,iditineraire,pointdepart,pointarrivee,dureeestimee,kilometrage) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, i.getIdUser());
            ste.setInt(2, i.getIdItineraire());
            ste.setString(3, i.getPointDepart());
            ste.setString(4, i.getPointDestination());
            ste.setInt(5, i.getDureeEstime());
            ste.setFloat(6, i.getKilometrage());
            ste.executeUpdate();
           
            System.out.println("********************itineraire ajoutée*******************************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @Override
    public void modifierItineraire(int id,itineraire i) {
    String sql = "update itineraire i join utilisateur r on (i.iduser=r.iduser) set pointdepart=?,pointarrivee=?,dureeestimee=?,kilometrage=?  where iditineraire=? ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, i.getPointDepart());
            ste.setString(2, i.getPointDestination());
            ste.setInt(3, i.getDureeEstime());
            ste.setFloat(4, i.getKilometrage());
            ste.setInt(5,id);
            ste.executeUpdate();
            System.out.println("********************** MODIFIED ****************************************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerItineraire(itineraire i) {

        String sql = "delete from itineraire where iditineraire=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, i.getIdItineraire());
            ste.executeUpdate();
            System.out.println("*********************** DELETED ************************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<itineraire> afficherItineraire() {

 List<itineraire> itineraires = new ArrayList<>();
            try {
            String sql = "select iduser,iditineraire,pointdepart,pointarrivee,kilometrage,dureeestimee from itineraire";
            Statement st = MyConnection.getInstance().getMyconnex().createStatement();
            ResultSet s = st.executeQuery(sql);
            while (s.next()) {

                itineraire i = new itineraire(s.getInt(1),s.getInt(2),s.getString(3),s.getString(4),s.getInt(5),s.getInt(6));
                itineraires.add(i);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return itineraires;
    }

    @Override
    public List<itineraire> Selectionparuser(int id) {
 List<itineraire> itineraires = new ArrayList<>();
        try {
            String sql = "select pointDepart,pointarrivee,dureeestimee from itineraire c where iduser=?";
           PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(4, id);
            ste.executeUpdate();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                itineraire i = new itineraire(s.getString(1),s.getString(2),s.getInt(3));
                itineraires.add(i);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return itineraires;
    }

public String NameUser(int id) {
    String a = "";
    try {
        String sql = "SELECT utilisateur.nom\n" +
            "FROM utilisateur\n" +
            "JOIN itineraire\n" +
            "ON utilisateur.iduser = itineraire.iduser\n" +
            "WHERE utilisateur.iduser=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet s = ste.executeQuery();
        while (s.next()) {
            a = s.getString("nom");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return a;
}
    public int findItineraireByUser(int iduser) {
        int a=-1;
        try {
            String sql ="SELECT iditineraire FROM itineraire i JOIN utilisateur u ON i.iduser = u.iduser WHERE u.iduser = ?";

            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, iduser);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                a = rs.getInt("iditineraire");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }

 
}
