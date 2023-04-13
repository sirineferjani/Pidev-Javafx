/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entitie.article;
import entitie.categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author user
 */
public class categorieService {
     Connection cnx = DataSource.getInstance().getCnx();
    
  public void ajouterCategorie(categorie c) {
    try { 
        String requete = "INSERT INTO categorie(nom_c,image_c)"+" VALUES (?,?)";
        PreparedStatement pst = new DataSource().getCnx().prepareStatement(requete);
    
       
        pst.setString(1, c.getNom_c());
        pst.setString(2, c.getImage_c());
       
        pst.executeUpdate();
        System.out.println("Categorie ajouté");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } 
}
    
    public ObservableList<categorie> afficherCategorie() {
       ObservableList<categorie> myList= FXCollections.observableArrayList();
        
    
        try {
            String sql="SELECT * FROM categorie";
            Statement ste=cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
                 categorie c = new categorie();
                 c.setId(rs.getInt(1));
                 c.setNom_c(rs.getString("nom_c")); 
                 c.setImage_c(rs.getString("image_c"));
         
                 

                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
     public void supprimerCategorie(categorie cat) {
        try {
            String req = "DELETE FROM `categorie` WHERE id = ?";
            PreparedStatement ste=cnx.prepareStatement(req);
            ste.setInt(1, cat.getId());
            ste.executeUpdate();
            System.out.println("Categorie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         public void modifierCategorie(categorie c) {
        String sql="update categorie set nom_c=?,image_c=?,where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getNom_c());
            ste.setString(2, c.getImage_c());
        
           
          
            ste.executeUpdate();
            System.out.println("Categorie modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         public categorie getCatParId(int id) {
    String sql = "SELECT * FROM categorie WHERE id = ?";
    PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            categorie c = new categorie(
                    rs.getInt("id"),
                    rs.getString("nom_c"),
                    rs.getString("image_c")
            );
            return c;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}
}
