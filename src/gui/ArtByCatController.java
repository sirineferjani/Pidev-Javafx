/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.article;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import service.articleService;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ArtByCatController implements Initializable {

    @FXML
    private FlowPane listprodbycat;
    public int idc;
    @FXML
    private TextField TFrechercheReca;
    @FXML
    private ComboBox<String> tricombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODo
        recherche();
        tricombo.getItems().addAll("Tri par prix croissant", "Tri par prix décroissant");
        tricombo.setValue("Tri par prix croissant");

        tricombo.setOnAction((event) -> {
            String selectedOption = tricombo.getValue();
            if (selectedOption.equals("Tri par prix croissant")) {
                trierParPrixCroissant();
            } else if (selectedOption.equals("Tri par prix décroissant")) {
                trierParPrixDécroissant();
            }
        });

    }

    public void cat(int id) {
        this.idc = id;
        System.out.println(idc);
        displayart();
    }

    private void displayart() {
        articleService ps = new articleService();
        ObservableList<article> lista = FXCollections.observableArrayList();

        lista = ps.findprodbycat(idc);
        //listp=ps.afficher();
        for (article a : lista) {
            VBox card = new VBox();
            card.setPrefSize(150, 100);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory + a.getImage())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel = new Label(a.getNom_article());
            namelabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Label prixLabel = new Label(Float.toString(a.getPrix()) + "DT");
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(prixLabel);
            if (a.getStock() == 0) {
                Label dispo = new Label("Out Of Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.RED);
                card.getChildren().add(dispo);
            } else {
                Label dispo = new Label("In Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.GREEN);
                card.getChildren().add(dispo);
                card.setOnMouseClicked((MouseEvent e) -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Detailart.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Detail Article");
                        DetailartController dpc = loader.getController();
                        dpc.setlabelprod(a);
                        Stage stage1 = (Stage) card.getScene().getWindow();
                        stage1.close();
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                });
            }
            Label catLabel = new Label(a.getCategorie().getNom_c());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);

            listprodbycat.getChildren().add(card);
            listprodbycat.setMargin(card, new Insets(5, 5, 5, 5));
        }
    }

    private Node createArticleNode(article article) throws FileNotFoundException {
        // Créer un VBox pour contenir le nom et le prix de l'article
        if (article == null) {
            return null;
        } else {
            VBox articleBox = new VBox();
            articleBox.setPrefSize(100, 100);
            articleBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            ImageView imageView;
            try {
                imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory + article.getImage())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                articleBox.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Créer des labels pour le nom et le prix de l'article
            Label namelabel = new Label(article.getNom_article());
            namelabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(namelabel);
            Label prixLabel = new Label(Float.toString(article.getPrix()) + "DT");
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(prixLabel);
            if (article.getStock() == 0) {
                Label dispo = new Label("Out Of Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.RED);
                articleBox.getChildren().add(dispo);
            } else {
                Label dispo = new Label("In Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.GREEN);
                articleBox.getChildren().add(dispo);
                articleBox.setOnMouseClicked((MouseEvent e) -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Detailart.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Detail Article");
                        DetailartController dpc = loader.getController();
                        dpc.setlabelprod(article);
                        Stage stage1 = (Stage) articleBox.getScene().getWindow();
                        stage1.close();
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                });
            }

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(articleBox);

            // Ajouter un style CSS au VBox pour qu'il soit bien présenté dans le FlowPane
            articleBox.setStyle("-fx-padding: 10px; -fx-background-color: #f2f2f2; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: #cccccc; -fx-border-width: 1px;");

            // Définir les contraintes de taille pour le VBox et l'ImageView
            articleBox.setPrefWidth(150);
            articleBox.setMaxWidth(150);
            listprodbycat.getChildren().add(articleBox);
            listprodbycat.setMargin(articleBox, new Insets(5, 5, 5, 5));

            // Retourner le StackPane contenant l'ImageView et le VBox
            return stackPane;
        }
    }

    private void recherche() {
        // Ajouter un listener sur le champ de recherche pour effectuer la recherche à chaque modification du texte
        TFrechercheReca.textProperty().addListener((observable, oldValue, newValue) -> {
            articleService sp = new articleService();
            // Filtrer les articles en utilisant le nouveau texte de recherche
            List<article> articlerecherche = sp.findprodbycat(idc).stream()
                    .filter(article -> article.getNom_article().toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList());

            // Vider le FlowPane actuel pour afficher les articles filtrés
            listprodbycat.getChildren().clear();
            for (article article : articlerecherche) {
                try {
                    Node articleNode = createArticleNode(article);
                    if (articleNode != null) {
                        listprodbycat.getChildren().add(articleNode); // ajouter le nouveau noeud dans le FlowPane
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            listprodbycat.layout();
        });
    }

    public void trierParPrixCroissant() {
        articleService ps = new articleService();
        ObservableList<article> lista = ps.findprodbycat(idc);

        List<article> articlesTriés = lista.stream()
                .sorted(Comparator.comparingInt(article::getPrix))
                .collect(Collectors.toList());

        listprodbycat.getChildren().clear();
        for (article article : articlesTriés) {
            try {
                VBox card = (VBox) createArticleNode(article);
                listprodbycat.getChildren().add(card);
                listprodbycat.setMargin(card, new Insets(5, 5, 5, 5));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArtByCatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void trierParPrixDécroissant() {
        articleService ps = new articleService();
        ObservableList<article> lista = ps.findprodbycat(idc);

        List<article> articlesTriés = lista.stream()
                .sorted(Comparator.comparingInt(article -> -article.getPrix()))
                .collect(Collectors.toList());

        listprodbycat.getChildren().clear();
        for (article article : articlesTriés) {
            try {
                VBox card = (VBox) createArticleNode(article);
                listprodbycat.getChildren().add(card);
                listprodbycat.setMargin(card, new Insets(5, 5, 5, 5));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArtByCatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Ajouter un message
        String message = "La liste des articles a été triée par ordre décroissant de prix.";
        System.out.println(message); // afficher le message dans la console
        // ou bien
        // labelMessage.setText(message); // afficher le message dans une étiquette
    }

}
