/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.article;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void cat(int id){
        this.idc=id;
        displayart();
    }
    private void displayart(){
        articleService ps=new articleService();
        ObservableList<article>lista=FXCollections.observableArrayList();
        lista=ps.findprodbycat(idc);
        //listp=ps.afficher();
        for(article a:lista){
            VBox card=new VBox();
            card.setPrefSize(150, 150);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
            
            
            ImageView imgview = null;
            try {
                ImageView imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+a.getImage())));
                imgview.setFitWidth(120);
                imgview.setFitHeight(80);
                imgview.setPreserveRatio(true);
                card.getChildren().add(imgview);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CatfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel=new Label(a.getNom_article());     
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Label prixLabel=new Label(Float.toString(a.getPrix())+"DT");
            prixLabel.setWrapText(true);
            prixLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(prixLabel);
            if(a.getStock()==0){
                Label dispo=new Label("Out Of Stock");
                dispo.setAlignment(Pos.CENTER);
              //  dispo.setTextFill(Color.RED);
                card.getChildren().add(dispo);
            }else{
                Label dispo=new Label("In Stock");
                dispo.setAlignment(Pos.CENTER);
              //  dispo.setTextFill(Color.GREEN);
                card.getChildren().add(dispo);
            }
            Label catLabel=new Label(a.getCategorie().getNom_c());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);
        
            listprodbycat.getChildren().add(card);
            listprodbycat.setMargin(card, new Insets(5, 5, 5, 5));
    }
    }

    
}
