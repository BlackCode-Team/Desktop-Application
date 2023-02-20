package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    String url = "jdbc:mysql://localhost:3306/carngo";
    String login = "root";
    String password = "";
    java.sql.Connection myconnex;
    public static MyConnection cnx;

    public MyConnection() {
        try {
            myconnex = DriverManager.getConnection(url, login, password);
            System.out.println("****************CONNETION TO DATABASE ************ ");
            System.out.println("****************   SUCCESSFULLY    ********* ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyConnection getInstance() {
        if (cnx == null) {
            cnx = new MyConnection();
        }
        return cnx;
    }

    public java.sql.Connection getCnx() {
        return myconnex;
    }

    
}
