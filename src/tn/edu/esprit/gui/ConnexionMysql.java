/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public class ConnexionMysql {
    public Connection cn = null;
    public static Connection connexionDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev" , "root", "");
            System.out.println("cnx reussie");
            return cn ;
        }catch(ClassNotFoundException |SQLException e ){
            System.out.println("connexion echouée");
            e.printStackTrace();
            return null;
        }
    }
    
}
