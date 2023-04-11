/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import tn.edu.esprit.entities.user;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class ProfileuserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private Label nameLabel;
    
    @FXML
    private Label Labelemail; 
    
    @FXML
    private Label adressLabel; 
      
 
      
  
    
    
    private user loggedInUser;
    
    public void setUserData(user user) throws IOException {
    loggedInUser = user;
    nameLabel.setText(loggedInUser.getNom());
    Labelemail.setText(loggedInUser.getEmail());
    adressLabel.setText(loggedInUser.getPrenom());
   
    }

    
     
}
