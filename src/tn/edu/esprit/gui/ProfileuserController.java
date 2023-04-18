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

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

    @FXML
    private ImageView qrCodeImageView;

    private user loggedInUser;

    public void setUserData(user user) throws IOException {
        loggedInUser = user;

        // Afficher les informations de l'utilisateur
        nameLabel.setText(loggedInUser.getNom());
        Labelemail.setText(loggedInUser.getEmail());
        adressLabel.setText(loggedInUser.getPrenom());

        try {
            // Charger l'image de l'utilisateur à partir du disque
            Image image = new Image(new FileInputStream("C:/imagepi/" + loggedInUser.getImage()));
            imageLogged.setImage(image);
            imageLogged.setFitWidth(150);
            imageLogged.setFitHeight(150);
            imageLogged.setClip(new Circle(75, 75, 75));
            imageLogged.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileuserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Générer le contenu du QR code
            String qrCodeContent = String.format("Nom : %s\nEmail : %s\nAdresse: %s",
                    loggedInUser.getNom(), loggedInUser.getEmail(), loggedInUser.getPrenom());

            // Créer un writer pour le QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // Générer la matrice de bits pour le contenu du QR code
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, com.google.zxing.BarcodeFormat.QR_CODE, 200, 200);

            // Convertir la matrice de bits en image BufferedImage
            BufferedImage qrCodeImage = toBufferedImage(bitMatrix);

            // Convertir l'image BufferedImage en image JavaFX et l'afficher dans l'interface utilisateur
            qrCodeImageView.setImage(SwingFXUtils.toFXImage(qrCodeImage, null));

        } catch (WriterException e) {
            e.printStackTrace();
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
    
    
    //La fonction toBufferedImage() prend une matrice de bits (BitMatrix) représentant un QR Code et la convertit en une image BufferedImage de taille égale contenant les données du QR Code.

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    /* @FXML
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
     */
}
