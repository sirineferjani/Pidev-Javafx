/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.article;
import entitie.categorie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.articleService;
import service.categorieService;
import utils.Statics;

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
    @FXML
    private Label nomerreur;
    @FXML
    private Label imgerreur;
    private File selectedFile=null;
    @FXML
    private Button aff1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
          public boolean estAlpha(String chaine) {
            return chaine.matches("[a-zA-Z]+");
        }
    @FXML
    private void ajoutcat(ActionEvent event) throws IOException {
        
         if(nomc.getText().trim().isEmpty() || nomc.getText().length() < 3)
        {
        nomerreur.setText("Entrer un nom supérieur a 3 catactéres\n");
        }
           if(imagec.getText().trim().isEmpty())
        {
            imgerreur.setText("Entrer une image\n");
        }
             if(nomc.getText().trim().isEmpty())
        {
            nomerreur.setText("Entrer le nom de la catégorie");
        }
             if(!estAlpha(nomc.getText()))
            {
                nomerreur.setText("seulement des alphabets");
            }
            StringBuilder errors=new StringBuilder();
        
      
        int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        String newFileName = random_int+"-"+selectedFile.getName();
        if(!nomc.getText().isEmpty()&&estAlpha(nomc.getText()))
        {String nom_c = nomc.getText();
        if(!estAlpha(nom_c))
        {
            nomerreur.setText("verifier seulement des alphabets");
        }
        String image_c = imagec.getText();
         categorie c = new categorie(nom_c,newFileName);
        categorieService cs = new categorieService();
        cs.ajouterCategorie(c);
        Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);
//
        Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        }
        
      
    }

    @FXML
    private void uploadimgc(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            imagec.setText(file.getName()); //badalna el input
            selectedFile = file;
        }
        
    }

    @FXML
    private void affichagec(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CatDispaly.fxml"));
            Parent root = loader.load();
            

          aff1.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
