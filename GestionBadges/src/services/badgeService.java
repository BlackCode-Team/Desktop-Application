/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author MSI
 */
public class badgeService implements Ibadge{
    Connection myconnex ;
    
    public badgeService(){
        myconnex = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public int ajouterBadge(Badge B) {
        int id=-1;
        try {
           String req1 ="INSERT INTO badge (idbadge,typebadge,nbpoint,iduser) VALUES (?,?,?,?)";
   
          PreparedStatement ste = myconnex.prepareStatement(req1);
          
          ste.setInt(1, B.getIdbadge());
          ste.setString(2, B.getTypebadge());
          
          ste.setInt(3, B.getNbrepoint());  
          ste.setInt(4, B.getIdUser());
          id= ste.executeUpdate();
          
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
       return id;
    }

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
        public void supprimerBadge2(Badge B) {
         String req3 = "delete from badge where idbadge=?";
        try {
            PreparedStatement ste = myconnex.prepareStatement(req3);
            ste.setInt(1, B.getIdbadge());
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
            String sql = "select typebadge,nbpoint,iduser from badge";
            Statement ste = myconnex.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Badge B = new Badge(s.getString(1),s.getInt(2),s.getInt(3));
                Badges.add(B);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Badges;
    }
    

}
