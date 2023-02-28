package services;

import entities.TypeVehicule;
import entities.Vehicule;
import entities.Voiture;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculeServices implements IServices<Vehicule> {
    Connection myconnex= MyConnection.getInstance().getMyconnex();

    @Override
    public boolean addEntity(Vehicule v) {
        try {
            String req1;
            if(v.getType()==TypeVehicule.voiture){
                 req1="INSERT INTO vehicule (type, modele, batterie, matricule, puissance, prix)  "+
                    " VALUES ( '"+v.getType()+"','"+v.getModele()+"','"+v.getBatterie()+"','"
                                +v.getMatricule()+"','"+v.getPuissance()+"','"+v.getPrix()+"');";}
            else{
                 req1="INSERT INTO `vehicule`(`type`, `modele`, `batterie`,matricule `maxspeed`,prix) "+
                        " VALUES ( '"+v.getType()+"','"+v.getModele()+"','"+v.getBatterie()+"','"+v.getMatricule()+"','"
                                    +v.getMaxspeed()+v.getPrix()+"');";
            }
            Statement ste= myconnex.createStatement();
            ste.executeUpdate(req1);
            System.out.println("Vehicule ajouté");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
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
        String idquery="select idvehicule from vehicule where matricule=?";
        String req="delete from vehicule where idvehicule=? ";
        try {
            //pour rec l'id
            PreparedStatement recupId = myconnex.prepareStatement(idquery);
            recupId.setString(1, v.getMatricule());
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
                if (v.getType()==TypeVehicule.voiture){
                    v.setModele(rstset.getString("modele"));
                    v.setBatterie(rstset.getInt("batterie"));
                    v.setMatricule(rstset.getString("matricule"));
                    v.setPuissance(rstset.getInt("puissance"));
                    v.setPrix(rstset.getInt("prix"));
                }
                else{
                    v.setModele(rstset.getString("modele"));
                    v.setBatterie(rstset.getInt("batterie"));
                    v.setMaxspeed(rstset.getInt("maxspeed"));
                    v.setMatricule(rstset.getString("matricule"));
                    v.setPrix(rstset.getInt("prix"));
                }
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Vehicule> findbylocation(String emplacement) {
        List<Vehicule> listVehicules=new ArrayList<>();
        String req="select * from vehicule v "+
                "INNER JOIN itineraire i ON v.iditineraire = i.iditineraire " +
                "WHERE i.pointarrivee = ?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, emplacement);
            ResultSet rstset = ps.executeQuery();
            while (rstset.next()){
                Vehicule v =new Vehicule();
                v.setType(TypeVehicule.valueOf(rstset.getString("type")));
                if(v.getType()==TypeVehicule.voiture){
                v.setModele(rstset.getString("modele"));
                v.setBatterie(rstset.getInt("batterie"));
                v.setMatricule(rstset.getString("matricule"));
                v.setPuissance(rstset.getInt("puissance"));
                }
                else {
                    v.setModele(rstset.getString("modele"));
                    v.setBatterie(rstset.getInt("batterie"));
                    v.setMaxspeed(rstset.getInt("maxspeed"));
                }
                listVehicules.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listVehicules;
    }

    public Voiture findVoiturebymatricule(String matricule) {
        Voiture v = null;
        try {
            String req= "SELECT * FROM vehicule WHERE matricule = ?";
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, matricule);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v = new Voiture();
                v.setType(TypeVehicule.valueOf(rs.getString("type")));
                v.setModele(rs.getString("modele"));
                v.setBatterie(rs.getInt("batterie"));
                v.setMatricule(rs.getString("matricule"));
                v.setPuissance(rs.getInt("puissance"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (v == null) {
            System.out.println("Aucune voiture trouvée pour cette matricule: " + matricule);
        } else {
            System.out.println("La voiture correspondante à cette matricule :" + matricule + " est :");
        }
        return v;
    }

}
