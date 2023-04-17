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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class UpdateArticleController implements Initializable {

    @FXML
    private TextField reffield;
    @FXML
    private TextField descfield;
    @FXML
    private TextField nomfield;
    @FXML
    private TextField prixfield;
    @FXML
    private TextField sotckfield;
    @FXML
    private TextField imagefied;
    @FXML
    private ComboBox<String> catcombo;
    @FXML
    private Button btnimg;
    @FXML
    private Button btnmodif;
    article a1;
    private File selectedFile=null;
    categorieService cs;
    @FXML
    private Button btnaffichage;

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
    }    

    @FXML
    private void addimgonclick(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            imagefied.setText(file.getName()); //badalna el input
            selectedFile = file;
        }
    }

    @FXML
    private void modifarticleonclick(ActionEvent event) {
        if(!imagefied.getText().equals(a1.getImage())){
            int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
          String newFileName = null;
                  //random_int+"-"+selectedFile.getName();
            String chaine = catcombo.getValue();
        int index = chaine.indexOf(":");
        String sousChaine = chaine.substring(0, index);
        System.out.println(sousChaine);
          categorie cat1=cs.getCatParId(19);
          article aa=new article(a1.getId(), Integer.parseInt(reffield.getText()), nomfield.getText(), descfield.getText(), Integer.parseInt(prixfield.getText()), newFileName, Integer.parseInt(sotckfield.getText()), cat1);
            articleService as=new articleService();
            as.modifier(aa);
        }else{
            String chaine = catcombo.getValue();
        int index = chaine.indexOf(":");
        String sousChaine = chaine.substring(0, index);
        System.out.println(sousChaine);
        cs=new categorieService();
          categorie cat2=cs.getCatParId(Integer.parseInt(sousChaine));
            System.out.println(cat2);
          article aa=new article(a1.getId(), Integer.parseInt(reffield.getText()), nomfield.getText(), descfield.getText(), Integer.parseInt(prixfield.getText()), imagefied.getText(), Integer.parseInt(sotckfield.getText()), cat2);
            articleService as=new articleService();
            as.modifier(aa);
        }
            
    }
    
    public void recupdata(article a){
        reffield.setText(Integer.toString(a.getRef_article()));
        nomfield.setText(a.getNom_article());
        prixfield.setText(Integer.toString(a.getPrix()));
        sotckfield.setText(Integer.toString(a.getStock()));
        descfield.setText(a.getDescription());
        imagefied.setText(a.getImage());
        String combo=a.getCategorie().getId()+":"+a.getCategorie().getNom_c();
        catcombo.setValue(combo);
        a1=a;
    }

    @FXML
    private void affichage(ActionEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("articledisplay.fxml"));
            Parent root = loader.load();
            

          btnaffichage.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
