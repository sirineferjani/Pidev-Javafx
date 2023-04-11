/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.edu.esprit.entities.user;
import tn.edu.esprit.services.ServicePersonne;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class LoginController implements Initializable {
   
    @FXML
    private TextField emaillogin;
    @FXML
    private   PasswordField pwdlogin;
    
    private ServicePersonne service = new ServicePersonne();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
@FXML

  public void handleLogin(ActionEvent event) throws SQLException {
    try {
        boolean result = service.login(emaillogin.getText(), pwdlogin.getText());
        if (result) {
            user u = service.getUserByEmail(emaillogin.getText());
            if(u.getRole().equals("Admin")) {
            Parent root = FXMLLoader.load(getClass().getResource("adminback.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            }
            else if(u.getRole().equals("User")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("profileuser.fxml"));
                Parent root = loader.load();
                ProfileuserController controller = loader.getController();
                controller.setUserData(u);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setContentText("Identifiant ou mot de passe incorrect");
            alert.showAndWait();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}}
