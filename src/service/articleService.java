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
        String requete = "INSERT INTO article(ref_article,nom_article,description,prix,image,stock,categories_id)"+" VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setObject(1, a.getRef_article());
        pst.setString(2, a.getNom_article());
        pst.setString(3, a.getDescription());
        pst.setInt(4, a.getPrix());
        pst.setString(5, a.getImage());
        pst.setInt(6, a.getStock());
        pst.setObject(7, a.getCategorie().getId());
        pst.executeUpdate();
        System.out.println("Article ajouté");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } 
}

    public ObservableList<article> afficherArticle() {
      ObservableList<article> myList= FXCollections.observableArrayList();
        
    
        try {
            String sql="SELECT * FROM article";
            Statement ste=cnx.createStatement();
            ResultSet rs= ste.executeQuery(sql);
            categorieService cs=new categorieService();
            while(rs.next()){
                 article a = new article();
                 a.setId(rs.getInt("id"));
                 a.setCategorie(cs.getCatParId(rs.getInt("categories_id")));
                 a.setRef_article(rs.getInt("ref_article"));
                 a.setNom_article(rs.getString("nom_article")); 
                 a.setDescription(rs.getString("description"));
                 a.setPrix(rs.getInt("prix"));
                 a.setImage(rs.getString("image"));
                  a.setStock(rs.getInt("stock"));
                 

                myList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
        public void supprimerArticle(article a) {
        String req = "DELETE FROM article WHERE id=?";
            try {
            PreparedStatement ste=cnx.prepareStatement(req);
            ste.setInt(1, a.getId());
            ste.executeUpdate();
            System.out.println("Article deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         public void modifier(article a) {
        String sql="update article set ref_article=?,nom_article=?,description=?,prix=?,image=?,stock=?,categories_id=? where id=? ";
        PreparedStatement ste ;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setObject(1, a.getRef_article());
            ste.setString(2, a.getNom_article());
            ste.setString(3, a.getDescription());
            ste.setInt(4, a.getPrix());
            ste.setString(5, a.getImage());
            ste.setInt(6, a.getStock());
            ste.setObject(7, a.getCategorie().getId());
            ste.setInt(8, a.getId());
          
            ste.executeUpdate();
            System.out.println("Article modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
         public ObservableList<article> findprodbycat(int idprod){
        ObservableList< article>prod=FXCollections.observableArrayList();
        String sql="select * from article where categories_id =?";
        PreparedStatement ste;
        categorieService cs=new categorieService();
        try{
            ste=cnx.prepareStatement(sql);
            ste.setInt(1, idprod);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                article c=new article(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getInt(7),cs.getCatParId(rs.getInt(8)));
                prod.add(c);
            }
           
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return prod;
    }
 

}
