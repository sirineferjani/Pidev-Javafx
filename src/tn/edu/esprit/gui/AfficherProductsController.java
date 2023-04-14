/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author lenovo
 */
class AfficherProductsController {
    @FXML
    private Label tfName;
    @FXML
    private Label tfDescription;
     @FXML
    private Label tfReference;
      @FXML
    private Label tfStatut;
       @FXML
    private Label tfPrice;
    
   

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setName(String name){
        tfName.setText(name);
    }
    
    public void setDescription(String description){
        tfDescription.setText(description);
    } 
    public void setReference(String reference){
        tfReference.setText(reference);
    } 
    public void setStatut(String statut){
        tfStatut.setText(statut);
    } 
    public void setPrice(String price){
        tfPrice.setText(price);
    } 
}
