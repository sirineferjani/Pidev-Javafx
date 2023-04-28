/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author abdelazizmezri
 */
public class DataSource {

   Connection cnx ;
   PreparedStatement st ;
   int myIndex;
   

    public Connection connect() {
        String USER = "root";
        String databaseName="pidev";
        String PASSWORD = "";
        String URL = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.jbdc.Driver");
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cnx;
    }

    

}
