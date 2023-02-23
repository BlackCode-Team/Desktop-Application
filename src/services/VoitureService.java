package services;

import entities.Trottinette;
import entities.TypeVehicule;
import entities.Vehicule;
import entities.Voiture;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoitureService implements IService<Voiture> {
    Connection myconnex= MyConnection.getInstance().getMyconnex();

    @Override
    public boolean addEntity(Voiture v) {
        try {
            String req1="INSERT INTO `vehicule`(`type`, `modele`, `batterie`, `matricule`, `puissance`) "+
                    " VALUES ( '"+v.getType()+"','"+v.getModele()+"','"+v.getBatterie()+"','"
                    +v.getMatricule()+"','"+v.getPuissance()+"');";
            Statement ste= myconnex.createStatement();
            ste.executeUpdate(req1);
            System.out.println("Voiture ajoutée");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateEntity(Voiture v) {
        String idquery="select idvehicule from vehicule where matricule= ?";
        String req = "UPDATE vehicule SET modele = ?, batterie = ?, matricule = ?, puissance = ? WHERE idvehicule = ?";
        try {
            //pour recuperer l'id
            PreparedStatement recupId = myconnex.prepareStatement(idquery);
            recupId.setString(1, v.getMatricule());
            ResultSet rs = recupId.executeQuery();
            if (!rs.next()) {
                System.out.println("Aucune voiture trouvée ");
                return false;
            }
            int id = rs.getInt("idvehicule");
            //pour req
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, v.getModele());
            ps.setInt(2, v.getBatterie());
            ps.setString(3, v.getMatricule());
            ps.setInt(4, v.getPuissance());
            ps.setInt(5, id);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Voiture modifiée");
                return true;
            } else {
                System.out.println("Aucune voiture modifiée");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteEntity(Voiture v) {
        String idquery="select idvehicule from vehicule where matricule=?";
        String req="delete from vehicule where idvehicule=? ";
        try {
            //pour rec l'id
            PreparedStatement recupId = myconnex.prepareStatement(idquery);
            recupId.setString(1, v.getMatricule());
            ResultSet rs = recupId.executeQuery();
            if (!rs.next()) {
                System.out.println("Aucune voiture trouvée ");
                return false;
            }
            int id = rs.getInt("idvehicule");
            //pour la req
            PreparedStatement ps=myconnex.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("voiture supprimée");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Voiture> findAllEntity() {
        List<Voiture> list=new ArrayList<>();
        String req="select * from vehicule";
        try {
            Statement ste=myconnex.createStatement();
            ResultSet rstset= ste.executeQuery(req);
            while (rstset.next()){
                Voiture v =new Voiture();
                v.setType(TypeVehicule.valueOf(rstset.getString("type")));
                v.setModele(rstset.getString("modele"));
                v.setBatterie(rstset.getInt("batterie"));
                v.setMatricule(rstset.getString("matricule"));
                v.setPuissance(rstset.getInt("puissance"));
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Voiture> findbylocation(String emplacement) {
    List<Voiture> listvoitures=new ArrayList<>();
    String req="select * from vehicule v "+
            "INNER JOIN itineraire i ON v.iditineraire = i.iditineraire " +
            "WHERE i.pointarrivee = ?";
        try {
            PreparedStatement ps = myconnex.prepareStatement(req);
            ps.setString(1, emplacement);
            ResultSet rstset = ps.executeQuery();
        while (rstset.next()){
            Voiture v =new Voiture();
            v.setType(TypeVehicule.valueOf(rstset.getString("type")));
            v.setModele(rstset.getString("modele"));
            v.setBatterie(rstset.getInt("batterie"));
            v.setMatricule(rstset.getString("matricule"));
            v.setPuissance(rstset.getInt("puissance"));
            listvoitures.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
    }
        return listvoitures;
    }

    public Voiture findvoiturebymatricule(String matricule) {
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
            System.out.println("La voiture correspondant à cette matricule :" + matricule + " est :");
        }
        return v;
    }

}
