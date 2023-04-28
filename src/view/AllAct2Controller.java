/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package view;

import entities.Activite;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import utils.MyDB;
import view.OneActiviteController.MyListener;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AllAct2Controller implements Initializable {

    private GridPane grid;

    private MyListener myListener;

    private List<Activite> Eve = new ArrayList<>();

    private Connection cnx=MyDB.getInstance().getcnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(this.getData());
        Eve = new ArrayList<>();

        Eve.addAll(getData());
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < Eve.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneActiviteController.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                OneActiviteController itemController = fxmlLoader.getController();
                itemController.setData(Eve.get(i), myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Activite> getData() {
        List<Activite> activite = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `activite`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Activite a = new Activite();
            a.setId(rs.getInt(1));
            a.setNom(rs.getString(2));
            a.setDescription(rs.getString(3));
            a.setAge(rs.getInt(4));
            a.setLieu(rs.getString(5));
            a.setImage(rs.getString(6));

            
            
            activite.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
        return activite;
    }

}
