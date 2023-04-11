/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import javafx.scene.input.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import tn.edu.esprit.entities.user;
import tn.edu.esprit.services.ServicePersonne;


/**
 * FXML Controller class
 *
 * @author nourb
 */
public class RegisterUserController implements Initializable {
   private String imguser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private   PasswordField tfMotDePasse;
     @FXML
    private   TextField tfemail;
        @FXML
     private ImageView imageView;
          @FXML
    private Label ImageName;
   
    /**
     * Initializes the controller class.
     */
    private boolean isValidInput() {
    if (tfNom.getText().isEmpty()) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Le champ Nom ne peut pas etre nulle", ButtonType.OK);
        a.showAndWait();
        return false;
    }
     if (tfPrenom.getText().isEmpty())
         {
        Alert a = new Alert(Alert.AlertType.ERROR, "Le champ adresse ne peut pas etre nulle", ButtonType.OK);
        a.showAndWait();
        return false;
    }
     if (tfemail.getText().isEmpty())
         {
        Alert a = new Alert(Alert.AlertType.ERROR, "Le champ adresse mail  ne peut pas etre nulle", ButtonType.OK);
        a.showAndWait();
        return false;}
    
   
    if (!tfemail.getText().matches("^\\S+@(esprit\\.tn|gmail\\.fr|gmail\\.com|yahoo\\.fr|yahoo\\.com)$")) {
    Alert a = new Alert(Alert.AlertType.ERROR, "Email invalide", ButtonType.OK);
    a.showAndWait();
    return false;
}

    if (tfMotDePasse.getText().length() < 5) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Le mot de passe doit contenir au moins 5 caractères", ButtonType.OK);
        a.showAndWait();
        return false;
    }
        else if (!Character.isUpperCase(tfMotDePasse.getText().charAt(0))) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Le mot de passe doit commencer par une majuscule", ButtonType.OK);
        a.showAndWait();
        return false;
    }
    else  if (!tfMotDePasse.getText().matches(".*[\\d\\W].*")) {
        Alert a = new Alert(Alert.AlertType.ERROR, "Le mot de passe doit contenir au moins un chiffre ou un symbole pour sa robustesse", ButtonType.OK);
        a.showAndWait();
        return false;
    }
    return true;
}


  /*  @FXML
    private void ajouterPersonne(ActionEvent event) throws IOException {
        if (!isValidInput()) {
        return;
    } {
            try {
                ServicePersonne sp = new ServicePersonne();
               String hashedPassword = hashPassword(tfMotDePasse.getText());
               
               
            // Sélectionne l'image et la charge dans l'élément ImageView de votre FXML
            // choisirImage(event);
               user p = new user(tfNom.getText(), tfPrenom.getText(), hashedPassword , tfemail.getText());
               p.setEmail(tfemail.getText());
                sp.ajouter(p);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Personne added !", ButtonType.OK);
                a.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                tfNom.getScene().setRoot(root);
                
                AfficherPersonneController apc = loader.getController();
                apc.setNom(tfNom.getText());
                apc.setPrenom(tfPrenom.getText());
                /*apc.setPassword(tfMotDePasse.getText());
                apc.setRole("User");
                apc.setEmail(tfemail.getText());*/
               // apc.setImage(imageView.getImage());
              
                
         /*   } catch (SQLException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }

    }*/
  @FXML
private void ajouterPersonne(ActionEvent event) throws IOException {
    if (!isValidInput()) {
        return;
    }

    // Vérifie si l'utilisateur a sélectionné une image
    if (imageView.getImage() == null) {
        Alert alert = new Alert(AlertType.WARNING, "Please select an image.");
        alert.showAndWait();
        return;
    }

    try {
        ServicePersonne sp = new ServicePersonne();
        String hashedPassword = hashPassword(tfMotDePasse.getText());

        // Sélectionne l'image et la charge dans l'élément ImageView de votre FXML
        choisirImage(event);

        // Convertit l'image en un tableau de bytes pour le stockage dans la base de données
        byte[] imageBytes = null;
        if (imageView.getImage() != null) {
            BufferedImage bImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", baos);
            imageBytes = baos.toByteArray();
        }

       user p = new user(tfNom.getText(), tfPrenom.getText(), hashedPassword , tfemail.getText(), imageBytes);
        
        
        sp.ajouter(p);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Personne added !", ButtonType.OK);
        a.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        tfNom.getScene().setRoot(root);

        AfficherPersonneController apc = loader.getController();
        apc.setNom(tfNom.getText());
        apc.setPrenom(tfPrenom.getText());
       // apc.setPassword(tfMotDePasse.getText());
       // apc.setRole("User");
       // apc.setEmail(tfemail.getText());
       // apc.setImage(imageView.getImage());

    } catch (SQLException ex) {
        Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
        a.showAndWait();
    }
}


  public static String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
        throw new RuntimeException(ex);
    }
}
      @FXML
   public void GoToLogin(MouseEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    Parent root = loader.load();
    LoginController cntr = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
      @FXML
private void choisirImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
        new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
    );
    File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
    if (selectedFile != null) {
        try {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Erreur lors du chargement de l'image.", ButtonType.OK);
            a.showAndWait();
        }
    }
}}

  
  /* fel fxml <ImageView fx:id="imageView" fitHeight="150.0" fitWidth="200.0" layoutX="343.0" layoutY="30.0" pickOnBounds="true" preserveRatio="true" />
      <Button fx:id="upload" layoutX="416.0" layoutY="220.0" mnemonicParsing="false" onAction="#browseButtonAction" style="-fx-background-color: #173ac6;" text="upload" textFill="#d4d7e1" />

wfel service nzid l'image fil requete
}*/

    

