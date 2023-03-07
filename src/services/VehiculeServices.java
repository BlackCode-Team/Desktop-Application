package services;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import utils.MyConnection;

import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VehiculeServices implements IServices<Vehicule> {
    Connection myconnex= MyConnection.getInstance().getMyconnex();

    @Override
    public boolean addEntity(Vehicule v) {
        try {
            String req1="INSERT INTO vehicule (type, modele, batterie, matricule, puissance, prix,image)  "+
                    " VALUES ( '"+v.getType()+"','"+v.getModele()+"','"+v.getBatterie()+"','"
                    +v.getMatricule()+"','"+v.getPuissance()+"','"+v.getPrix()+"','"+v.getImg()+"');";
            Statement ste= myconnex.createStatement();
            ste.executeUpdate(req1);
            System.out.println("Vehicule ajouté");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public  String loadImage(String imagePath, ImageView imageView) {
        String imageFileName = new File(imagePath).getName();
        String imageDirPath = new File(imagePath).getParent();
        String dbImagePath = null;
        try  {
            // Insert the image path into the database
            String sql = "INSERT INTO images (filename, filepath) VALUES (?, ?)";
            PreparedStatement stmt = myconnex.prepareStatement(sql);
            stmt.setString(1, imageFileName);
            stmt.setString(2, imageDirPath);
            stmt.executeUpdate();
            // Retrieve the auto-generated primary key value for the image record
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int imageId = rs.getInt(1);
                dbImagePath = String.format("/images/%d/%s", imageId, imageFileName);
                // Load the image into the ImageView
                Image image = new Image(new File(imagePath).toURI().toString());
                imageView.setImage(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbImagePath;
    }

    @Override
    public boolean updateEntity(Vehicule v) {
        String idquery="select idvehicule from vehicule where modele= ?";
        String req;
        if(v.getType()==TypeVehicule.voiture){
            req = "UPDATE vehicule SET modele = ?, batterie = ?, matricule = ?, puissance = ? WHERE idvehicule = ?";}
        else{
            req = "UPDATE vehicule SET modele = ?, batterie = ?, maxspeed = ? WHERE idvehicule = ?";
        }
        try {
            //pour recuperer l'id
            PreparedStatement recupId = myconnex.prepareStatement(idquery);
            recupId.setString(1, v.getModele());
            ResultSet rs = recupId.executeQuery();
            if (!rs.next()) {
                System.out.println("Aucun véhicule trouvé ");
                return false;
            }
            int id = rs.getInt("idvehicule");
            //pour req
            PreparedStatement ps = myconnex.prepareStatement(req);
            if (v.getType()==TypeVehicule.voiture){
                ps.setString(1, v.getModele());
                ps.setInt(2, v.getBatterie());
                ps.setString(3, v.getMatricule());
                ps.setInt(4, v.getPuissance());
                ps.setInt(5, id);
            } else {
                ps.setString(1,v.getModele());
                ps.setInt(2,v.getBatterie());
                ps.setInt(3,v.getMaxspeed());
                ps.setInt(4, id);
            }
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Vehicule modifié");
                return true;
            } else {
                System.out.println("Aucun vehicule modifié");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteEntity(Vehicule v) {
        String idquery="select idvehicule from vehicule where modele=?";
        String req="delete from vehicule where idvehicule=? ";
        try {
            //pour rec l'id
            PreparedStatement recupId = myconnex.prepareStatement(idquery);
            recupId.setString(1, v.getModele());
            ResultSet rs = recupId.executeQuery();
            if (!rs.next()) {
                System.out.println("Aucun véhicule trouvé ");
                return false;
            }
            int id = rs.getInt("idvehicule");
            //pour la req
            PreparedStatement ps=myconnex.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("véhicule supprimé");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public List<Vehicule> findAllEntity() {
        List<Vehicule> list=new ArrayList<>();
        String req="select * from vehicule";
        try {
            Statement ste=myconnex.createStatement();
            ResultSet rstset= ste.executeQuery(req);
            while (rstset.next()){
                Vehicule v =new Vehicule();
                v.setType(TypeVehicule.valueOf(rstset.getString("type")));
                v.setModele(rstset.getString("modele"));
                v.setBatterie(rstset.getInt("batterie"));
                v.setMatricule(rstset.getString("matricule"));
                v.setPuissance(rstset.getInt("puissance"));
                v.setStatus(StatusVehicule.fromString(rstset.getString("status")));
                v.setPrix(rstset.getInt("prix"));
                v.setImg(rstset.getString("image"));
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Vehicule> findbylocation(int iditineraire) {
        List<Vehicule> listVehicules = new ArrayList<>();
        String req = "SELECT v.*, i.pointdepart " +
                "FROM vehicule v " +
                "INNER JOIN itineraire i ON i.iditineraire = v.iditineraire " +
                "WHERE i.iditineraire = ?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setInt(1, iditineraire);
            ResultSet rstset = ps.executeQuery();
            while (rstset.next()){
                Vehicule v = new Vehicule();
                v.setType(TypeVehicule.valueOf(rstset.getString("type")));
                v.setModele(rstset.getString("modele"));
                v.setBatterie(rstset.getInt("batterie"));
                v.setStatus(StatusVehicule.fromString(rstset.getString("status")));
                v.setMatricule(rstset.getString("matricule"));
                v.setPuissance(rstset.getInt("puissance"));
                v.setImg(rstset.getString("image"));

                listVehicules.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listVehicules;
    }

    public List<Vehicule> findbyType(TypeVehicule typeVehicule) {
        List<Vehicule> listVehicules=new ArrayList<>();
        String req="select * from vehicule v where type = ?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, String.valueOf(typeVehicule));
            ResultSet rstset = ps.executeQuery();
            while (rstset.next()){
                Vehicule v =new Vehicule();
                v.setModele(rstset.getString("modele"));
                v.setBatterie(rstset.getInt("batterie"));
                v.setPrix(rstset.getInt("prix"));
                v.setType(TypeVehicule.valueOf(rstset.getString("type")));
                v.setStatus(StatusVehicule.fromString(rstset.getString("status")));
                v.setMatricule(rstset.getString("matricule"));
                v.setPuissance(rstset.getInt("puissance"));
                v.setImg(rstset.getString("image"));
                listVehicules.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listVehicules;
    }

//    public List<Vehicule> afficherVehiculesTriesParPrix(TypeVehicule typeVehicule) {
//        List<Vehicule> listVehicules = findbyType(typeVehicule);
//        Collections.sort(listVehicules, Comparator.comparingInt(Vehicule::getPrix));
//        for (Vehicule v : listVehicules) {
//            System.out.println(v.getModele() + " - " + v.getPrix() + "€");
//        }
//        return listVehicules;
//    }

    public List<Vehicule> afficherVehiculesTriesParPrix(TypeVehicule typeVehicule, boolean croissant) {
        List<Vehicule> listVehicules = findbyType(typeVehicule);
        Comparator<Vehicule> comparateurPrix = Comparator.comparingInt(Vehicule::getPrix);
        if (!croissant) {
            comparateurPrix = comparateurPrix.reversed();
        }
        Collections.sort(listVehicules, comparateurPrix);
        return listVehicules;
    }

    public List<Vehicule> findbyStatus(StatusVehicule statusVehicule) {
        List<Vehicule> listVehicules=new ArrayList<>();
        String req="select * from vehicule v where status = ?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, String.valueOf(statusVehicule));
            ResultSet rstset = ps.executeQuery();
            while (rstset.next()){
                Vehicule v =new Vehicule();
                v.setModele(rstset.getString("modele"));
                v.setBatterie(rstset.getInt("batterie"));
                v.setStatus(StatusVehicule.fromString(rstset.getString("status")));
                v.setMatricule(rstset.getString("matricule"));
                v.setPuissance(rstset.getInt("puissance"));
                v.setImg(rstset.getString("image"));
                listVehicules.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listVehicules;
    }

    public List<Vehicule> findbymatricule(String matricule) {
        List<Vehicule> listVehicules=new ArrayList<>();
        try {
            String req= "SELECT * FROM vehicule WHERE matricule = ?";
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, matricule);
            ResultSet rstset = ps.executeQuery();
            while (rstset.next()){
                Vehicule v =new Vehicule();
                v.setModele(rstset.getString("modele"));
                v.setBatterie(rstset.getInt("batterie"));
                v.setMatricule(rstset.getString("matricule"));
                v.setStatus(StatusVehicule.fromString(rstset.getString("status")));
                v.setPuissance(rstset.getInt("puissance"));
                v.setImg(rstset.getString("image"));
                listVehicules.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listVehicules;
    }

    public int calculConsommationEnergie(Vehicule v, int iditineraire) {
        String req = "SELECT v.batterie, v.puissance, i.kilometrage " +
                "FROM vehicule v " +
                "INNER JOIN itineraire i ON v.iditineraire = i.iditineraire " +
                "WHERE i.iditineraire = ?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setInt(1, iditineraire);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setBatterie(rs.getInt("batterie"));
                v.setPuissance(rs.getInt("puissance"));
                double kilometrage = rs.getDouble("kilometrage");
                double consommationmoy = (double) v.getPuissance() / 100.0;
                double consommationEnergie=consommationmoy * kilometrage;
                return (int) (consommationEnergie / v.getBatterie() * 100)-v.getBatterie();
            } else {
                // Aucun résultat trouvé
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int calculTVehicule(TypeVehicule typeVehicule){
        List<Vehicule> listVehicules = findbyType(typeVehicule);
        return listVehicules.size();
    }

    public int findIdVoiturebymatricule(String matricule) {
        int id = -1;

        try {
            String req= "SELECT idvehicule FROM vehicule WHERE matricule = ?";
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, matricule);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id=  rs.getInt("idvehicule");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }


}
