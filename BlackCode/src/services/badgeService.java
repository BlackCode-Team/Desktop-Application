package services;

import entities.Badge;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

public class badgeService implements Ibadge{
    Connection myconnex ;
    
    public badgeService(){
        myconnex = MyConnection.getInstance().getMyconnex();
    }
    
    // ajout d'un nouveau badge à la base de données;;;
    @Override
    public int ajouterBadge(Badge B) {
        int id=-1;
        try {
           String req1 ="INSERT INTO badge (idbadge,typebadge,nbpoint) VALUES (?,?,?)";
   
          PreparedStatement ste = myconnex.prepareStatement(req1);
          
          ste.setInt(1, B.getIdbadge());
          ste.setString(2, B.getTypebadge());
          ste.setInt(3, B.getNbrepoint());  
     
          id= ste.executeUpdate();
          
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
       return id;
    }

    // modification d'un badge 
    @Override
    public void modifierBadge(int idbadge,Badge B) {
        String req2 = "update badge set typebadge=?,nbpoint=? where idbadge=?";
        try {
            PreparedStatement ste = myconnex.prepareStatement(req2);
            ste.setString(1, B.getTypebadge());
            ste.setInt(2, B.getNbrepoint());
            ste.setInt(3, idbadge);
            ste.executeUpdate();
            System.out.println("*************** MODIFIED ********************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    // supprimer badge en donnant l'id du badge à supprimer
    @Override
    public void supprimerBadge(int idbadge) {
         String req3 = "delete from badge where idbadge=?";
        try {
            PreparedStatement ste = myconnex.prepareStatement(req3);
            ste.setInt(1, idbadge);
            ste.executeUpdate();
            System.out.println("*************** DELETED ****************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    // supprimer badge en donnant un objet badge en parametre 
        public void supprimerBadge2(Badge B) {
         String req3 = "delete from badge where typebadge=?";
        try {
            PreparedStatement ste = myconnex.prepareStatement(req3);
            ste.setString(1, B.getTypebadge());
            ste.executeUpdate();
            System.out.println("*************** DELETED ****************");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Badge> afficherBadge() {
        List<Badge> Badges = new ArrayList<>();
        try {
            String sql = "select idbadge,typebadge,nbpoint from badge";
            Statement ste = myconnex.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Badge B = new Badge(s.getInt(1),s.getString(2),s.getInt(3));
                Badges.add(B);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Badges;
    }
    
    
    public List<String> getTypesBadge() {
    List<String> typesBadge = new ArrayList<>();
    
    try {
         Statement stmt = myconnex.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT typebadge FROM badge");

        while (rs.next()) {
            typesBadge.add(rs.getString("typebadge"));
        }

    } catch (SQLException e) {
        System.err.println("Error retrieving badge types: " + e.getMessage());
    }

    return typesBadge;
}
  
    public int getIdByTypeBadge(String typebadge) {
    int idbadge = -1;
    try {
        String sql = "SELECT idbadge FROM badge WHERE typebadge = ?";
        PreparedStatement pstmt = myconnex.prepareStatement(sql);
        pstmt.setString(1, typebadge);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            idbadge = rs.getInt("idbadge");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de l'ID du badge : " + ex.getMessage());
    }
    return idbadge;
}

    

}
