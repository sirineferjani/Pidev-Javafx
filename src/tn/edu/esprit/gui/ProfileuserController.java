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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import tn.edu.esprit.entities.user;
import static tn.edu.esprit.gui.RegisterUserController.hashPassword;
import tn.edu.esprit.services.ServicePersonne;

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

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button saveButton;

    private user loggedInUser;

    public void setUserData(user user) throws IOException {
        loggedInUser = user;
        nameLabel.setText(loggedInUser.getNom());
        Labelemail.setText(loggedInUser.getEmail());
        adressLabel.setText(loggedInUser.getPrenom());

    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) throws SQLException {

        String newName = nameField != null ? nameField.getText() : null;
        String newEmail = emailField != null ? emailField.getText() : null;
        String newAddress = addressField != null ? addressField.getText() : null;
        String newPassword = PasswordField != null ? PasswordField.getText() : null;

        if (newName != null) {
            loggedInUser.setNom(newName);
        }

        if (newEmail != null) {
            loggedInUser.setEmail(newEmail);
        }

        if (newAddress != null) {
            loggedInUser.setPrenom(newAddress);
        }
        if (newPassword != null) {
            loggedInUser.setPassword(newPassword);
            String hashedPassword = hashPassword(newPassword);
            loggedInUser.setPassword(hashedPassword);
        }

        ServicePersonne service = new ServicePersonne();
        service.modifier(loggedInUser);

        try {
            setUserData(loggedInUser);
        } catch (IOException e) {
            // handle the exception as appropriate for your application
        }
    }

  @FXML
private void eklebAction(ActionEvent event) {
    nameField.setVisible(!nameField.isVisible());
    emailField.setVisible(!emailField.isVisible());
    addressField.setVisible(!addressField.isVisible());
    PasswordField.setVisible(!PasswordField.isVisible());
    saveButton.setVisible(!saveButton.isVisible());
}

    @FXML
    public void GoToUpdate(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateprofile.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
