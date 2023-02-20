/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.carngo.services;

import tn.esprit.carngo.entities.Personne;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.carngo.utilis.myConnection;

/**
 *
 * @author ychaa
 */
public class PersonneService implements IPersonneService{
    //Connection myConnex;
    Connection myConnex=  myConnection.getInstance().getConnection();
    @Override
    public int ajouterPersonne(Personne p) {
        int id=1;
        try{
        String req1 = "INSERT INTO `personne` (`id`, `nom`, `age`)"
                + " VALUES (NULL, '"+p.getNom()+"', '"+p.getAge()+"'); ";
        Statement ste = myConnex.createStatement();
        id =ste.executeUpdate(req1);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public boolean modifierPersonne(int id,Personne p) {
      boolean succes = false; 
      try{ String req1 = "UPDATE personne set  nom=? , age=?  WHERE id =? ";
       PreparedStatement ste = myConnex.prepareStatement(req1);
       ste.setString(1, p.getNom());
    ste.setInt(2, p.getAge());
    ste.setInt(3, id);
    int rowsUpdated = ste.executeUpdate();
    if (rowsUpdated > 0) {
      succes = true;
    }
      }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return succes;
    }
    
    
    
    

    @Override
    public boolean supprimerPersonne(Personne p) {
       try {
           String req="delete from personne where id= ? ";
           PreparedStatement ps = myConnex.prepareStatement(req);
           ps.setInt(1,3 );
           ps.executeUpdate();
    
       } catch (SQLException ex) {
           System.out.println(ex);
       }
       
       return false;
    }


    @Override
    public List<Personne> afficherPersonnes() {
        List<Personne> list = new ArrayList<>();
       try { 
           String req= "select * from personne" ;
           Statement ste = myConnex.createStatement();
           ResultSet res= ste.executeQuery(req);
            while(res.next()){
                Personne p = new Personne();
                p.setNom(res.getString ("nom"));
                p.setAge(res.getInt("age"));
                list.add(p);
            }
        } catch (SQLException ex) {
        }
       return list;
    }
    
}
