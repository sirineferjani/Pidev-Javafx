/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.categorie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.categorieService;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdatecatController implements Initializable {

    @FXML
    private Label erreurnom;
    @FXML
    private TextField fieldnom;
    @FXML
    private TextField filedimg;
    @FXML
    private Button btnupdateimg;
    @FXML
    private Button btnmodifiercat;
    private File selectedFile=null;
    categorie c1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choisirimgupdate(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal

        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            //Image image1 = new Image(file.toURI().toString());
            //addImage.setImage(image1);//badalna el image
            filedimg.setText(file.getName()); //badalna el input
            selectedFile = file;
        }

    }

    @FXML
    private void updatecatonclick(ActionEvent event) throws IOException {
        if(!filedimg.getText().equals(c1.getImage_c())){
            int random_int = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
          String newFileName = random_int+"-"+selectedFile.getName();
          categorie cc=new categorie(c1.getId(), fieldnom.getText(), newFileName);
            categorieService cs=new categorieService();
            cs.modifierCategorie(cc);
            Path sourceFile = Paths.get(selectedFile.toPath().toString());
        Path targetFile = Paths.get(Statics.uploadDirectory+newFileName);
//
        Files.copy(sourceFile, targetFile,StandardCopyOption.REPLACE_EXISTING);
        }
        else{
            categorie cc=new categorie(c1.getId(), fieldnom.getText(), filedimg.getText());
            categorieService cs=new categorieService();
            cs.modifierCategorie(cc);
        }
            
    }
    
    public void getdata(categorie cat){
        fieldnom.setText(cat.getNom_c());
        filedimg.setText(cat.getImage_c());
        c1=cat;
    }
    
}
