/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class WindowController implements Initializable {
    private Parent fxml;
     @FXML
    private Button btncheck;
       @FXML
    private AnchorPane root;

    @FXML
    void chechHomePage(ActionEvent event) {
        try{
            fxml =FXMLLoader.load(getClass().getResource("../gui/products.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
            
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
