/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.article;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.articleService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ArticleController implements Initializable {

    @FXML
    private TextField refa;
    @FXML
    private TextField noma;
    @FXML
    private TextField prixa;
    @FXML
    private TextField dsea;
    @FXML
    private TextField imagea;
    @FXML
    private TextField sa;
    @FXML
    private Button btnaj;
    @FXML
    private Button btnaff;
    @FXML
    private Button upload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutarticle(ActionEvent event) {
     int ref_article=Integer.parseInt(refa.getText());
        String nom_article = noma.getText();
        String description = dsea.getText();
     int prix=   Integer.parseInt(prixa.getText());
        String image = imagea.getText();
       int stock= Integer.parseInt(sa.getText());
        article a = new article(ref_article,nom_article ,description, prix,image,stock);
        articleService ps = new articleService();
        ps.ajouterArticle(a);
    }

    @FXML
    private void afficherarticle(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affarticle.fxml"));
            Parent root = loader.load();
            

          btnaff.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void uploadimg(ActionEvent event) {
          final FileChooser fileChooser = new FileChooser(); 
        final Stage stage = null;

        File file = fileChooser.showOpenDialog(stage); 
        if (file != null) { 
            imagea.setText(file.getName()); 
              File selectedFile = file;
        }
    }
    
}
