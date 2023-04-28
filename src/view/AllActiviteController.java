/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package view;

import view.OneActiviteController.MyListener;
import entities.Activite;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AllActiviteController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    private MyListener myListener;

    private List<Activite> Eve = new ArrayList<>();
    
    
            Connection cnx = MyDB.getInstance().getcnx();
            
            
             private List<Activite> getData() {
     List<Activite> eev = new ArrayList<>();
     Activite Eev;
     try {
            String req = "SELECT * from activite";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {        
                Eve.add(new Activite(rs.getInt("id"),
                        rs.getInt("age"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getString("lieu"),
                        rs.getString("image")));
                                

            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(eev);
    return eev;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Eve.addAll(getData());
        int column = 0;
        int row = 1;

         try {
            for (int i = 0; i < Eve.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneActiviteController.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                OneActiviteController itemController = fxmlLoader.getController();
                    itemController.setData(Eve.get(i),myListener);

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
    
}
