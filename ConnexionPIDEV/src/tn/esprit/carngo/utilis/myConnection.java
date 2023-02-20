/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.carngo.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ychaa
 */
public class myConnection {
 Connection myconnex;
static String url="jdbc:mysql://localhost:3306/carngo";
static String login="root";
static String password="";
static myConnection monCon;
    private myConnection() {
    
    try {
        myconnex = DriverManager.getConnection(url,login, password);
    System.out.println("reussie!");}
catch(SQLException e){
    System.out.println(e.getMessage());
                }
    }    
    public static myConnection getInstance(){
        if(monCon ==null)
            monCon=new myConnection();
        return monCon;
    }
      public Connection getConnection(){
        return myconnex;
    }
}
