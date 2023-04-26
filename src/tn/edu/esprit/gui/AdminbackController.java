/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import tn.edu.esprit.entities.user;
import tn.edu.esprit.services.ServicePersonne;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class AdminbackController implements Initializable {

    @FXML
    private FlowPane catpane;
    private boolean estBloque = false;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refrechpane();
    }

    public void refrechpane() {
        catpane.getChildren().clear();
        ServicePersonne sp = new ServicePersonne();
        ObservableList<user> listuser = FXCollections.observableArrayList();
        listuser = sp.afficherusers();

        for (user us : listuser) {
            VBox card = new VBox();
            card.setPrefSize(250, 250);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream("C:/imagepi/" + us.getImage())));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                imageView.setClip(new Circle(75, 75, 75));
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdminbackController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Label nameLabel = new Label(us.getNom());
            Label emailLabel = new Label(us.getEmail());
            Label adresseLabel = new Label(us.getPrenom());

            nameLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            nameLabel.setAlignment(Pos.CENTER);
            nameLabel.setStyle("-fx-text-fill: gray;");

            emailLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            emailLabel.setAlignment(Pos.CENTER);
            emailLabel.setStyle("-fx-text-fill: gray;");

            adresseLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
            adresseLabel.setAlignment(Pos.CENTER);
            adresseLabel.setStyle("-fx-text-fill: gray;");

            card.getChildren().add(nameLabel);
            card.getChildren().add(emailLabel);
            card.getChildren().add(adresseLabel);

            Button btn1 = new Button("Supprimer");
            btn1.setAlignment(Pos.TOP_RIGHT);
            btn1.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn1.setOnAction(e -> {
                ServicePersonne ps = new ServicePersonne();
                ps.supprimeruser(us);
                refrechpane();
            });
            Label messageLabel = new Label("");

            Button btn2 = new Button("Bloquer");
            btn2.setAlignment(Pos.TOP_RIGHT);
            btn2.setStyle("-fx-background-color: #ff0000;; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn2.setOnAction(e -> {
                ServicePersonne ps = new ServicePersonne();
                ps.Ban(us);
                btn2.setText("Bloqué");
                btn2.setStyle("-fx-background-color: #cccccc;; -fx-background-radius: 25px; -fx-text-fill: white;");
                messageLabel.setText("Utilisateur " + us.getNom() + " bloqué !");
                refrechpane();
            });

// Ajouter la label à votre interface utilisateur
            card.getChildren().addAll(btn2, messageLabel);

            card.getChildren().add(btn1);
            catpane.getChildren().add(card);
            catpane.setMargin(card, new Insets(5, 5, 5, 5));
        }
    }
    
}
