/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.shape.Circle;
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

    @FXML
    private ImageView imageLogged;

    private user loggedInUser;

    public void setUserData(user user) throws IOException {
        loggedInUser = user;
        nameLabel.setText(loggedInUser.getNom());
        Labelemail.setText(loggedInUser.getEmail());
        adressLabel.setText(loggedInUser.getPrenom());
        try {
            Image image = new Image(new FileInputStream("C:/imagepi/" + loggedInUser.getImage()));
            imageLogged.setImage(image);
            imageLogged.setFitWidth(150);
            imageLogged.setFitHeight(150);
            imageLogged.setClip(new Circle(75, 75, 75));
            imageLogged.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) throws SQLException {
        //Nullpointerexception
        String newName = nameField != null ? nameField.getText() : null;//La partie avant le ? vérifie si nameField n'est pas nul, et si c'est le cas, elle récupère le texte à partir de nameField et l'assigne à newName. Sinon, elle assigne null à newName
        String newEmail = emailField != null ? emailField.getText() : null;
        String newAddress = addressField != null ? addressField.getText() : null;
        String newPassword = PasswordField != null ? PasswordField.getText() : null;

      if (newName != null && !newName.isEmpty()) { //si nameField est null, alors newName sera null, la condition sera fausse et le bloc if ne sera pas exécuté.
    loggedInUser.setNom(newName);
}

if (newEmail != null && !newEmail.isEmpty()) {
    loggedInUser.setEmail(newEmail);
}

if (newAddress != null && !newAddress.isEmpty()) {
    loggedInUser.setPrenom(newAddress);
}

if (newPassword != null && !newPassword.isEmpty()) {
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
    @FXML
private void handleLogoutButtonAction(ActionEvent event) throws IOException {
    // Réinitialiser l'utilisateur actuellement connecté
    loggedInUser = null;
    
    // Rediriger vers la page de connexion
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}

}
