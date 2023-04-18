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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODo
    }    
      public void cat(int id){
        this.idc=id;
        System.out.println(idc);
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
            
            
            ImageView imageView;
            try {
               imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+a.getImage())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
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
                dispo.setTextFill(Color.RED);
                card.getChildren().add(dispo);
            }else{
                Label dispo=new Label("In Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.GREEN);
                card.getChildren().add(dispo);
                card.setOnMouseClicked((MouseEvent e) ->{
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Detailart.fxml"));
                try{
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Detail Article");
                    DetailartController dpc=loader.getController();
                    dpc.setlabelprod(a);
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                            stage.show();
                }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            }
            Label catLabel=new Label(a.getCategorie().getNom_c());
            catLabel.setAlignment(Pos.CENTER);
            card.getChildren().add(catLabel);
        
            listprodbycat.getChildren().add(card);
            listprodbycat.setMargin(card, new Insets(5, 5, 5, 5));
    }
    }

    
}
