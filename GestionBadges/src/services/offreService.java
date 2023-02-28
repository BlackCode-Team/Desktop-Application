package services;

import entities.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

public class offreService implements Ioffre {

    Connection myconnex;

    public offreService() {
        myconnex = MyConnection.getInstance().getCnx();
    }

    @Override
    public int ajouterOffre(Offre O) {
        int id = -1;
        try {
            String req1 = "INSERT INTO offre (idoffre,nom,description,idbadge) VALUES (?,?,?,?)";

            PreparedStatement ste = myconnex.prepareStatement(req1);

            ste.setInt(1, O.getIdoffre());
            ste.setString(2, O.getNom());
            ste.setString(3, O.getDescription());
            ste.setInt(4, O.getIdbadge());

            id = ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    @Override
    public void modifierOffre(int idoffre, Offre O) {
        String req2 = "update offre set nom=?,description=?, idbadge=? where idoffre=?";
        try {
            PreparedStatement ste = myconnex.prepareStatement(req2);
            ste.setString(1, O.getNom());
            ste.setString(2, O.getDescription());
            ste.setInt(3, O.getIdbadge());
            ste.setInt(4, idoffre);
            ste.executeUpdate();
            System.out.println("*************** MODIFIED ********************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerOffre(int idoffre) {
        String req3 = "delete from offre where idoffre=?";
        try {
            PreparedStatement ste = myconnex.prepareStatement(req3);
            ste.setInt(1, idoffre);
            ste.executeUpdate();
            System.out.println("*************** DELETED ****************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public List<Offre> afficherOffre() {
//        List<Offre> offres = new ArrayList<>();
//        Offre o = new Offre();
//        try {
//            //String sql = "select idoffre,nom,description,idbadge from offre";
//            String sql = "SELECT o.idoffre, o.nom, o.description, b.typebadge FROM offre o JOIN badge b ON o.idbadge = b.idbadge ";
//            Statement ste = myconnex.createStatement();
//            ResultSet s = ste.executeQuery(sql);
//            while (s.next()) {
//               Offre O = new Offre(s.getInt(1),s.getString(2),s.getString(3),s.getString(4));
//                    String typebadge = s.getString("typebadge");
//                    o.setTypebadge(typebadge); 
//                    offres.add(o);
//                }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return offres;
//    }
//        @Override
//    public List<Offre> afficherOffre() {
//        List<Offre> offres = new ArrayList<>();
//        Offre o = new Offre();
//        try {
//            String sql = "select idoffre,nom,description,idbadge from offre";
//            Statement ste = myconnex.createStatement();
//            ResultSet s = ste.executeQuery(sql);
//            while (s.next()) {
//            Offre O = new Offre(s.getInt(1),s.getString(2),s.getString(3),s.getInt(4));
//           String req2 = "SELECT o.idoffre o.nom, o.description, b.typebadge FROM offre o JOIN badge b ON o.idbadge = b.idbadge ";
//                PreparedStatement ps2 = myconnex.prepareStatement(req2);
//                ResultSet resu2 = ps2.executeQuery();
//                while (resu2.next()) {
//                    String typebadge = resu2.getString("typebadge");
//                    o.setTypebadge(typebadge); }
//                    offres.add(o);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return offres;
//    }
    @Override
    public List<Offre> afficherOffre() {
        List<Offre> offres = new ArrayList<>();
        try {
            //String sql = "select idoffre,nom,description,idbadge from offre";
            String sql = "SELECT o.idoffre, o.nom, o.description, b.typebadge FROM offre o JOIN badge b ON o.idbadge = b.idbadge ";
            PreparedStatement ps = myconnex.prepareStatement(sql);
            ResultSet resu = ps.executeQuery();
            while (resu.next()) {
                Offre o = new Offre();
                o.setIdoffre(resu.getInt("idoffre"));
                o.setNom(resu.getString("nom"));
                o.setDescription(resu.getString("description"));
                String typebadge = resu.getString("typebadge");
                o.setTypebadge(typebadge);
                offres.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }

}
