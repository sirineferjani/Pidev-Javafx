/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entitie.article;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
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
            String requete = "INSERT INTO article(ref_article,nom_article,description,prix,image,stock,categories_id)" + " VALUES (?,?,?,?,?,?,?)";
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
        ObservableList<article> myList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM article";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            categorieService cs = new categorieService();
            while (rs.next()) {
                article a = new article();
                a.setId(rs.getInt("id"));
                a.setCategorie(cs.getCatParId(rs.getInt("categories_id")));
                a.setRef_article(rs.getInt("ref_article"));
                a.setNom_article(rs.getString("nom_article"));
                a.setDescription(rs.getString("description"));
                a.setPrix(rs.getInt("prix"));
                a.setImage(rs.getString("image"));
                a.setStock(rs.getInt("stock"));
                a.setNote(rs.getFloat("note"));

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
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, a.getId());
            ste.executeUpdate();
            System.out.println("Article deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(article a) {
        String sql = "update article set ref_article=?,nom_article=?,description=?,prix=?,image=?,stock=?,categories_id=? where id=? ";
        PreparedStatement ste;
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

    public ObservableList<article> findprodbycat(int idprod) {
        ObservableList< article> prod = FXCollections.observableArrayList();
        String sql = "select * from article where categories_id =?";
        PreparedStatement ste;
        categorieService cs = new categorieService();
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, idprod);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                article c = new article(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), cs.getCatParId(rs.getInt(8)));
                prod.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prod;
    }

    public List<article> Rechercher(String critere, String rec) {

        List<article> Articles = new ArrayList();

        try {
            Statement stm = cnx.createStatement();
            String querry = "SELECT * FROM article where " + critere + " like '" + rec + "%'";

            ResultSet rs = stm.executeQuery(querry);

            while (rs.next()) {
                article A = new article();

                A.setNom_article(rs.getString(1));
                A.setDescription(rs.getString(2));
                A.setPrix(rs.getInt(3));
                A.setImage(rs.getString(4));
                A.setStock(rs.getInt(5));

                Articles.add(A);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return Articles;

    }

    public article findartbyid(int id) throws SQLException {
        String sql = "SELECT * from article where id=?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        categorieService cs = new categorieService();
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            article c = new article(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getFloat(9), cs.getCatParId(resultSet.getInt(8)));
            //System.out.println(resultSet.getFloat(sql));
            return c;
        } else {
            return null;
        }
    }

    public void updaterate(int id, float rate) {
        article pp = null;
        try {
            pp = findartbyid(id);
        } catch (SQLException ex) {
            Logger.getLogger(articleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(pp.getNote());
        if (pp.getNote() == 0) {
            String sql = "update article set note=? where id=? ";
            PreparedStatement ste;
            try {
                ste = cnx.prepareStatement(sql);
                ste.setFloat(1, rate);
                ste.setInt(2, id);
                ste.executeUpdate();
                System.out.println("note modifiée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            String sql = "update article set note=? where id=? ";
            PreparedStatement ste;
            try {
                ste = cnx.prepareStatement(sql);
                ste.setFloat(1, (rate + pp.getNote()) / 2);
                ste.setInt(2, id);
                ste.executeUpdate();
                System.out.println("note modifiée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
    }
     public void notifyUser(String message) {
        if (SystemTray.isSupported()) {
            try {
                // Initialiser SystemTray
                SystemTray tray = SystemTray.getSystemTray();

                // Créer une icône pour la notification
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon trayIcon = new TrayIcon(image, "Notification");

                // Ajouter l'icône au SystemTray
                tray.add(trayIcon);

                // Afficher la notification
                trayIcon.displayMessage("Notification", message, TrayIcon.MessageType.INFO);
            } catch (AWTException e) {
                System.err.println("Erreur lors de l'initialisation du SystemTray: " + e);
            }
        } else {
            System.out.println("SystemTray n'est pas pris en charge");
        }
    }
     
     public ObservableList<article> findrecommendedart() {
        ObservableList<article> myList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM article where note>3";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            categorieService cs = new categorieService();
            while (rs.next()) {
                article a = new article();
                a.setId(rs.getInt("id"));
                a.setCategorie(cs.getCatParId(rs.getInt("categories_id")));
                a.setRef_article(rs.getInt("ref_article"));
                a.setNom_article(rs.getString("nom_article"));
                a.setDescription(rs.getString("description"));
                a.setPrix(rs.getInt("prix"));
                a.setImage(rs.getString("image"));
                a.setStock(rs.getInt("stock"));
                a.setNote(rs.getFloat("note"));

                myList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
