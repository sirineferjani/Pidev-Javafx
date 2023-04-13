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
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
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
public class ArticledisplayController implements Initializable {

    @FXML
    private FlowPane articlepane;
    ObservableList<article>listprod=FXCollections.observableArrayList();
    //List<article>listprod=new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            addtopane();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void addtopane() throws FileNotFoundException{
       articlepane.getChildren().clear();
         articleService cs=new articleService();
        ObservableList<article>listart=FXCollections.observableArrayList();
        listart=cs.afficherArticle();    
        for(article art:listart){
                VBox card=new VBox();
                card.setPrefSize(150, 150);
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                ImageView imageView;
            try {
               imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+art.getImage())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel=new Label(art.getNom_article());    
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Button btn=new Button("Edit");
            btn.setAlignment(Pos.TOP_RIGHT);
            btn.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn.setOnAction(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateArticle.fxml"));
                    Parent root = loader.load();
                    //artegoryupdateController cuc=loader.getController();
                    //cuc.setData(art);
                    UpdateArticleController uac=loader.getController();
                            uac.recupdata(art);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit artegorie");
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(UpdateArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            card.getChildren().add(btn);
            Button btn1=new Button("Supprimer");
            btn1.setAlignment(Pos.TOP_RIGHT);
             btn1.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn1.setOnAction(e->{
                articleService as=new articleService();
                as.supprimerArticle(art);
                    try {
                        addtopane();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
            card.getChildren().add(btn1);
            articlepane.getChildren().add(card);
            articlepane.setMargin(card, new Insets(5, 5, 5, 5));
     }}
    
    private void showProductDetails(article a) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Product Details");
    alert.setHeaderText(a.getNom_article());
    alert.setContentText("Description: " + a.getDescription() + "\nPrix: " +a.getPrix()+"DT"+"\nQuantite:"+ a.getStock()+"\nNote:"+a.getNote());
    alert.show();
}

    
}
