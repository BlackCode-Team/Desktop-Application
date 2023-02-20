package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    Connection myconnex;
    String url="jdbc:mysql://localhost:3306/carngo";
    String login="root";
    String password="0000";
    Connection Myconnex;

    static MyConnection moncon;

    private MyConnection() {
        try {
            Myconnex= DriverManager.getConnection(url,login,password);
            System.out.println("connexion etablie!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static MyConnection getInstance(){
        if(moncon==null)
            moncon= new MyConnection();
        return moncon;
    }
    public Connection getMyconnex() {
        return Myconnex;
    }
}
