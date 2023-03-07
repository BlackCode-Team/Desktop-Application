package services;

import entities.PlaceStationnement;
import entities.Vehicule;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceStationnementServices implements IServices<PlaceStationnement> {
    Connection myconnex= MyConnection.getInstance().getMyconnex();
    @Override
    public boolean addEntity(PlaceStationnement ps) {
        String req = "INSERT INTO placestationnement (location) VALUES (?)";
        try {
            PreparedStatement psmt = myconnex.prepareStatement(req);
            psmt.setString(1, ps.getLocation());
            psmt.executeUpdate();
            System.out.println("place stationnement ajoutee");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean updateEntity(PlaceStationnement ps) {
        String req="Update placestationnement set location=? where idplace=?";
        try{
            PreparedStatement ste=myconnex.prepareStatement(req);
            ste.setString(1,ps.getLocation());
            ste.setInt(2,ps.getId());
            ste.executeUpdate();
            System.out.println("modification effectuée!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteEntity(PlaceStationnement ps) {
        String req="Delete from placestationnement where idplace=?";
        try{
            PreparedStatement prp=myconnex.prepareStatement(req);
            prp.setInt(1,ps.getId());
            prp.executeUpdate();
            System.out.println("suppression effectuee");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<PlaceStationnement> findAllEntity() {
        String req = "select p.location, v.matricule FROM placestationnement p " +
                "INNER JOIN vehicule v ON v.idvehicule = p.idvehicule" ;

        List<PlaceStationnement> places= new ArrayList<>();
        try {
            PreparedStatement prp = myconnex.prepareStatement(req);
            ResultSet rstset = prp.executeQuery();
            while (!rstset.next()) {
                PlaceStationnement p = new PlaceStationnement();
                p.setLocation(rstset.getString("location"));
                Vehicule v=new Vehicule();
                v.setMatricule(rstset.getString("matricule"));
                //p.setMatricules(v.getMatricule());
                places.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return places;
    }


}
