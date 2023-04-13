/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Activite;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceAc;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class ActiviteController implements Initializable {

    @FXML
    private TextField idac;
    @FXML
    private TextField nomac;
    @FXML
    private TextField desc;
    @FXML
    private TextField age;
    @FXML
    private Button su;
    @FXML
    private Button aj;
    @FXML
    private Button mod;
    @FXML
    private TextField lieu;
    @FXML
    private TextField img;
    @FXML
    private TableColumn<Activite, String> ida;
    @FXML
    private TableColumn<Activite, String> noma;
    @FXML
    private TableColumn<Activite, String> agea;
    @FXML
    private TableColumn<Activite, String> desca;
    @FXML
    private TableColumn<Activite, String> lieua;
    @FXML
    private TableColumn<Activite, String> imga;
    @FXML
    private TableView<Activite> affiche;
    
    
    
    public void refreshTable() {
    ServiceAc us = new ServiceAc();
    List<Activite> l = new ArrayList<>();
    l = (ArrayList<Activite>) us.afficherAc();
    ObservableList<Activite> data = FXCollections.observableArrayList(l);
    FilteredList<Activite> fle = new FilteredList(data, e -> true);
    ida.setCellValueFactory(new PropertyValueFactory<>("id"));
    noma.setCellValueFactory(new PropertyValueFactory<>("nom"));
    agea.setCellValueFactory(new PropertyValueFactory<>("age"));
    desca.setCellValueFactory(new PropertyValueFactory<>("description"));
    lieua.setCellValueFactory(new PropertyValueFactory<>("lieu"));
    imga.setCellValueFactory(new PropertyValueFactory<>("image"));

    affiche.setItems(fle);
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshTable();
    }    

    @FXML
    private void supp(ActionEvent event)throws IOException {
        
         ServiceAc sm = new ServiceAc() ;   

            

      StringBuilder errors=new StringBuilder();
        
        if(idac.getText().trim().isEmpty()){
            errors.append("Please enter an id\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
                   sm.supprimerAc(Integer.parseInt(idac.getText()));

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is deleted successfully!");
            alert.show(); 
            
            refreshTable();
    }


    @FXML
    private void ajt(ActionEvent event) throws IOException {
         ServiceAc sm = new ServiceAc() ;   
    Activite c = new Activite() ;
           
     
        StringBuilder errors=new StringBuilder();
        
        if(nomac.getText().trim().isEmpty()&&desc.getText().trim().isEmpty()){
            errors.append("svp entrer un nom et la description\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Activite is added successfully!");
            alert.show(); 
            
      
      c.setNom(nomac.getText());
      c.setAge(Integer.parseInt(age.getText()));
      c.setDescription(desc.getText());
      c.setLieu(lieu.getText());
      c.setImage(img.getText());

         
            sm.ajouterAc(c);
                       
            refreshTable();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, ParseException {
        
    Activite c = new Activite();
    StringBuilder errors = new StringBuilder();
        
    if(idac.getText().trim().isEmpty()){
        errors.append("Please enter an id\n");
    }
        
    // Vérifier si le champ nom est vide ou a une taille incorrecte
    if(nomac.getText().trim().isEmpty() || nomac.getText().length() < 3 || nomac.getText().length() > 9){
        errors.append("Please enter a name between 4 and 9 characters\n");
    }
     
    if(errors.length() > 0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else { // Si la validation est réussie, effectuer la modification
        ServiceAc sm = new ServiceAc() ;  
         
        c.setId(Integer.parseInt(idac.getText()));   
       c.setNom(nomac.getText());

        c.setAge(Integer.parseInt(age.getText()));
        c.setDescription(desc.getText());
        c.setLieu(lieu.getText());
        c.setImage(img.getText());
        
        sm.modiferAc(c);
        
        refreshTable();
    }
}

    @FXML
    private void rtr(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IntrActvAgc.fxml"));
                Parent root = loader.load();
                IntrActvAgcController a = loader.getController();
                age.getScene().setRoot(root);
    }
    
}
