/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.edu.esprit.entities.Products;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PanierController implements Initializable {

    @FXML
    private ImageView exit;
     @FXML
    private TextField setForm;

   
    @FXML
    private TextField messageBodyTextField;
      @FXML
    private TextField subjectTextField;


    @FXML
    private TextField setdescription;

    @FXML
    private TextField setname;

    @FXML
    private TextField setprice;
    @FXML
    private ImageView setImage;

    @FXML
    private HBox cardLayout;
    @FXML
    private VBox slider;

    @FXML
    private Button btnBuy;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;
    @FXML
    private VBox productLayout;
    @FXML
    private Button BtnMoins;

    @FXML
    private Button PlusBtn;

    @FXML
    private Label delete;

    @FXML
    void AddMoins(ActionEvent event) {

    }

    @FXML
    void AddPlus(ActionEvent event) {

    }

    @FXML
    void bills(MouseEvent event) {

    }

    @FXML
    void contact(MouseEvent event) {

    }

    @FXML
    private Button checkBtn;
    @FXML
    private GridPane panierContainer;
    private Parent fxml;
    private List<Products> recommended;

    @FXML
    void basket(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Panier.fxml"));
            slider.getChildren().removeAll();
            slider.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CheckOut(ActionEvent event) throws AddressException, MessagingException {
        Message message = new MimeMessage(Session.getDefaultInstance(new Properties()));
        message.setFrom(new InternetAddress("mezzicyrine897@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("cyrine.mezzi@esprit.tn"));
        message.setSubject(subjectTextField.getText());
        message.setText(messageBodyTextField.getText());
        
        new Thread(() -> {
    try {
        Transport.send(message);
        Platform.runLater(() -> {
            // Display a success message to the user
        });
    } catch (Exception ex) {
        Platform.runLater(() -> {
            // Display an error message to the user
        });
    }
}).start();

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void BuyIt(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Payment.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.append("erreur d'affichage");
            e.printStackTrace();
        }
    }

    public void MyFunction(String name, String description, Image image, String price) {
        setname.setText(name);
        setdescription.setText(description);
        setprice.setText(price);
        setImage.setImage(image);
    }

    @FXML
    void home(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/HomePage.fxml"));
            slider.getChildren().removeAll();
            slider.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void payment(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Payment.fxml"));
            slider.getChildren().removeAll();
            slider.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
