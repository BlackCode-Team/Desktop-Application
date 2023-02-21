/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.carngo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.ClassNotFoundException;

/**
 *
 * @author mhcab
 */
public class MyConnection {

    String url = "jdbc:mysql://localhost:3306/carngo1";
    String login = "root";
    String password = "";
    Connection myconnex;
    public static MyConnection ct;

    public MyConnection() {
        try {
            myconnex = DriverManager.getConnection(url, login, password);
            System.out.println("****************CONNETION TO DB ************ ");
            System.out.println("****************   SUCCESSFULLY    ********* ");

        } catch (SQLException ex) {
            System.out.println( ex.getMessage());
             Logger.getLogger(MyConnection.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static MyConnection getInstance() {
        if (ct == null) {
            ct = new MyConnection();
        }
        return ct;
    }

    public Connection getCnx() {
        return myconnex;
    }

}
