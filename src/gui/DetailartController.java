/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.article;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DetailartController implements Initializable {

    @FXML
    private AnchorPane pan;
    @FXML
    private ImageView artview;
    @FXML
    private Label nomart;
    @FXML
    private Label prix;
    @FXML
    private Label description;
    article art;
    @FXML
    private Label dispoprodlabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setlabelprod(article a){
        
        try {
            artview.setImage(new Image(new FileInputStream(Statics.uploadDirectory1+a.getImage())));
            artview.setFitWidth(200);
            artview.setFitHeight(200);
            artview.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        art=a;
        nomart.setText(a.getNom_article());
        nomart.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        prix.setText(String.format("%.2f DT", a.getPrix()));
        prix.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        description.setText(a.getDescription());
        description.setWrapText(true);
        description.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
       
        if(a.getStock()==0){
            dispoprodlabel.setText("Out Of Stock");
            dispoprodlabel.setTextFill(Color.RED);
        }else{
            dispoprodlabel.setText("In Stock");
            dispoprodlabel.setTextFill(Color.GREEN);
        }
//        VBox labelsVBox = new VBox(10, nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        HBox contentHBox = new HBox(20, prodimgview, labelsVBox);
//        //contentHBox.setPadding(new Insets(20));
//        hboxlabe.getChildren().add(contentHBox);
//        vboxlabe.getChildren().addAll(nomprodlabel, prixprodlabel, descriptionprodlabel, dispoprodlabel);
//        Pane parentPane = new Pane(contentHBox);
//        parentPane.setPrefSize(500, 500);
        
        
        
        
}
    
}
