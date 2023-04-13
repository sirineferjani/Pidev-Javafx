/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amirov
 */
public class MyDB {
    
    public  String url="jdbc:mysql://localhost:3306/gestion";
    public String login="root";
    public  String pwd="";
    private static Connection connexion;
    public static MyDB instance;
    
    private MyDB(){
        try {
            connexion =DriverManager.getConnection(url, login, pwd);
            if(connexion!=null){
                System.out.println("Cnx etablie ...");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("ERREUR");
            
        }
    }
    
    
    public Connection getcnx (){
        return connexion;
    }
    public static MyDB getInstance(){
        if(instance== null){
            instance = new MyDB();
        }
        return instance;
    }

    
    
    
    
}
