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
import static java.lang.Float.isNaN;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox<String> catcombo;
    @FXML
    private Label erreurref;
    @FXML
    private Label erreurstock;
    @FXML
    private Label erreurnom;
    @FXML
    private Label erreurprix;
    @FXML
    private Label erreurimg;
    @FXML
    private Label erreurdesc;
    private File selectedFile;
    categorieService cs;
    @FXML
    private Button cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categorieService cs=new categorieService();
        ObservableList<categorie> l= cs.afficherCategorie();
        ObservableList<String> nc = FXCollections.observableArrayList();
            for (categorie category : l) {
                String s=category.getId()+":"+category.getNom_c() ;
                 nc.add(s);
            }
            catcombo.setItems(nc);
            catcombo.setValue(nc.get(0));
             prixa.setText("0");
             sa.setText("0");
             refa.setText("0");
    }    
public boolean estAlpha(String chaine) {
            return chaine.matches("[a-zA-Z]+");
        }
        public boolean testpos(float d){
            if(d>1&&d!=0){
                return true;
            }else return false;
            
        }
 public boolean estEntier(int entier) {
    /*
     * Vérifie si l'entier est valide.
     * Retourne true si l'entier est valide, false sinon.
     */
    try {
        Integer.toString(entier);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
//public boolean testpos(float d){
//          if(d>1&&d!=0){
//            return true;
//      }else return false;}
    @FXML
    private void ajoutarticle(ActionEvent event) {
        erreurdesc.setText("");
        erreurnom.setText("");
        erreurprix.setText("");
        erreurstock.setText("");
 if(estAlpha(noma.getText())&& dsea.getText().length()>=10 && testpos(Integer.parseInt(prixa.getText())) && testpos(Float.parseFloat(sa.getText())) && Integer.parseInt(sa.getText())!=0 && !imagea.getText().isEmpty()){
        
        int ref_article=Integer.parseInt(refa.getText());
        String nom_article = noma.getText();
        String description = dsea.getText();
        int prix=   Integer.parseInt(prixa.getText());
        String image = imagea.getText();
        int stock= Integer.parseInt(sa.getText());
        String chaine = catcombo.getValue();
        int index = chaine.indexOf(":");
        String sousChaine = chaine.substring(0, index);
        System.out.println(sousChaine);
        cs=new categorieService();
          categorie cat2=cs.getCatParId(Integer.parseInt(sousChaine));
      
            int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
            String newFileName = random_int+"-"+selectedFile.getName();
            article a = new article(ref_article,nom_article ,description, prix,newFileName,stock,cat2);
            articleService ps = new articleService();
            ps.ajouterArticle(a);
            Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory   +newFileName);
        try {
            Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        } 
 
 }
    else{
      if(!estAlpha(noma.getText())&&noma.getText().isEmpty()){
                erreurnom.setText("Seulement des alphabets");
            }
     
          String refString = refa.getText();
if (!refString.isEmpty()) {
    try {
        int ref_article = Integer.parseInt(refString);
        // Vérifier si le prix est un entier valide ici
        // ...
    } catch (NumberFormatException e) {
        erreurref.setText("La référence doit être un entier");
    }
} else {
    erreurref.setText("le champ ne doit pas être vide");
}
            if(!estAlpha(dsea.getText())&&dsea.getText().length()<10&&dsea.getText().isEmpty()){
                erreurdesc.setText("Seulement des alphabets et un nombre de caractere sup a 10");
            }

            if(imagea.getText().isEmpty()){
                erreurimg.setText("Veuillez choisir une image");
            }

         
            if(!estAlpha(dsea.getText())&&dsea.getText().isEmpty()){
                erreurdesc.setText("Seulement des alphabets");
            }
            if(dsea.getText().length()<10&&dsea.getText().isEmpty()){
                erreurdesc.setText("Il faut un nombre de caractere sup a 10");
            }
        
            String hh=prixa.getText();
            hh+="0";
            if(!testpos(Integer.parseInt(hh))||prixa.getText().isEmpty()){
                erreurprix.setText("Il faut un prix positif");
            }
            String xx=sa.getText();
            xx+="0";
            if(!testpos(Integer.parseInt(xx))&&Integer.parseInt(xx)==0||sa.getText().isEmpty()){
               erreurstock.setText("Il faut une quantite positif et quantite sup a 0");
            }
            
            if(!testpos(Integer.parseInt(xx))||sa.getText().isEmpty()){
                erreurstock.setText("Il faut une quantite positif"); 
            }
            if(Integer.parseInt(xx)==0||sa.getText().isEmpty()){
                erreurstock.setText("Quantite sup a 0");
            }
            
        }

         
    }

    @FXML
    private void afficherarticle(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("articledisplay.fxml"));
            Parent root = loader.load();
            

          btnaff.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void uploadimg(ActionEvent event) {
         final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            imagea.setText(file.getName()); //badalna el input
            selectedFile = file;
        }
        
       
    }

    @FXML
    private void Gestioncategorie(ActionEvent event) {
                 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));
            Parent root = loader.load();
            

          cat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
