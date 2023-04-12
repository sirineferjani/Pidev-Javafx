/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entitie.article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javax.swing.UIManager.getString;
import utils.DataSource;

/**
 *
 * @author user
 */
public class articleService {
    Connection cnx = DataSource.getInstance().getCnx();
    
  public void ajouterArticle(article a) {
    try { 
        String requete = "INSERT INTO article(ref_article,nom_article,description,prix,image,stock)"+" VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = new DataSource().getCnx().prepareStatement(requete);
        pst.setObject(1, a.getRef_article());
        pst.setString(2, a.getNom_article());
        pst.setString(3, a.getDescription());
        pst.setInt(4, a.getPrix());
        pst.setString(5, a.getImage());
        pst.setInt(6, a.getStock());
        pst.executeUpdate();
        System.out.println("Article ajouté");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } 
}

    public List<article> afficherArticle() {
      List<article> myList= new ArrayList<>();
        
    
        try {
            String sql="SELECT * FROM article";
            Statement ste=cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
                 article a = new article();
                 a.setRef_article(rs.getInt("ref_article"));
                 a.setNom_article(rs.getString("nom_article")); 
                 a.setDescription(rs.getString("description"));
                 a.setPrix(rs.getInt("prix"));
                 a.setImage("image");
                  a.setStock(rs.getInt("stock"));
                 

                myList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
        public void supprimerArticle(int id) {
        try {
            String req = "DELETE FROM `article` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Article deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         public void modifier(article a) {
        String sql="update article set ref_article=?,nom_article=?,description=?,prix=?,image=?,stock=?,where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setObject(1, a.getRef_article());
            ste.setString(2, a.getNom_article());
            ste.setString(3, a.getDescription());
            ste.setInt(4, a.getPrix());
            ste.setString(5, a.getImage());
            ste.setInt(6, a.getStock());
           
          
            ste.executeUpdate();
            System.out.println("Article modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
