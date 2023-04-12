/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.article;
import entitie.categorie;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.articleService;
import service.categorieService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField nomc;
    @FXML
    private TextField imagec;
    @FXML
    private Button btn;
    @FXML
    private Button uploadimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutcat(ActionEvent event) {
         String nom_c = nomc.getText();
        String image_c = imagec.getText();
         categorie c = new categorie(nom_c,image_c);
        categorieService cs = new categorieService();
        cs.ajouterCategorie(c);
    }

    @FXML
    private void uploadimgc(ActionEvent event) {
           final FileChooser fileChooser = new FileChooser(); 
        final Stage stage = null;

        File file = fileChooser.showOpenDialog(stage); 
        if (file != null) { 
            imagec.setText(file.getName()); 
              File selectedFile = file;
        }
    }
    
}
