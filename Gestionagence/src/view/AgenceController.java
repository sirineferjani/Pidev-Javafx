/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Agence;
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
import services.ServiceAg;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class AgenceController implements Initializable {

    @FXML
    private TextField idag;
    @FXML
    private TextField nomag;
    @FXML
    private TextField dev;
    @FXML
    private TextField nbr;
    @FXML
    private Button su;
    @FXML
    private Button aj;
    @FXML
    private Button mod;
    @FXML
    private TextField tlf;
    @FXML
    private TextField aid;
    @FXML
    private TableColumn<Agence, String> idagg;
        @FXML
    private TableColumn<Agence, String> nomagg;
    @FXML
    private TableColumn<Agence, String> nbra;
    @FXML
    private TableColumn<Agence, String> deva;
    @FXML
    private TableColumn<Agence, String> tlfa;
    @FXML
    private TableColumn<Agence, String> acta;
    @FXML
    private TableView<Agence> affiche;
    
     public void refreshTable() {
    ServiceAg us = new ServiceAg();
    List<Agence> l = new ArrayList<>();
    l = (ArrayList<Agence>) us.afficherAg();
    ObservableList<Agence> data = FXCollections.observableArrayList(l);
    FilteredList<Agence> fle = new FilteredList(data, e -> true);
    idagg.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomagg.setCellValueFactory(new PropertyValueFactory<>("nom"));
    nbra.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));
    deva.setCellValueFactory(new PropertyValueFactory<>("devis"));
    tlfa.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
    acta.setCellValueFactory(new PropertyValueFactory<>("activite_id"));

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
    private void supp(ActionEvent event) throws IOException {
        
         ServiceAg sm = new ServiceAg() ;   

            

      StringBuilder errors=new StringBuilder();
        
        if(idag.getText().trim().isEmpty()){
            errors.append("Please enter an id\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
                   sm.supprimerAg(Integer.parseInt(idag.getText()));

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is deleted successfully!");
            alert.show(); 
            
            refreshTable();
    }


    @FXML
    private void ajt(ActionEvent event) throws IOException {
         ServiceAg sm = new ServiceAg() ;   
    Agence c = new Agence() ;
           
     
        StringBuilder errors=new StringBuilder();
        
        if(nomag.getText().trim().isEmpty()&&tlf.getText().trim().isEmpty()){
            errors.append("svp entrer un nom et le numero de telephone\n");
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
            
      
      c.setNom(nomag.getText());
      c.setNbr_participant(Integer.parseInt(nbr.getText()));
      c.setDevis(Integer.parseInt(dev.getText()));
      c.setNum_tel(Integer.parseInt(tlf.getText()));
      //c.setActivite_id(Integer.parseInt(aid.getText()));


         
            sm.ajouterAg(c);
                       
            refreshTable();
    }

    @FXML
    private void modifier(ActionEvent event)throws IOException, ParseException {
        
    Agence c = new Agence();
    StringBuilder errors = new StringBuilder();
        
    if(idag.getText().trim().isEmpty()){
        errors.append("Please enter an id\n");
    }
        
    // Vérifier si le champ nom est vide ou a une taille incorrecte
    if(nomag.getText().trim().isEmpty() || nomag.getText().length() < 3 || nomag.getText().length() > 9){
        errors.append("Please enter a name between 4 and 9 characters\n");
    }
     
    if(errors.length() > 0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else { // Si la validation est réussie, effectuer la modification
        ServiceAg sm = new ServiceAg() ;  
         
        c.setId(Integer.parseInt(idag.getText()));   
       c.setNom(nomag.getText());
      c.setNbr_participant(Integer.parseInt(nbr.getText()));
      c.setDevis(Integer.parseInt(dev.getText()));
      c.setNum_tel(Integer.parseInt(tlf.getText()));
      //c.setActivite_id(Integer.parseInt(aid.getText()));

        
        sm.modiferAg(c);
        
        refreshTable();
    }
}


    @FXML
    private void rtr(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IntrActvAgc.fxml"));
                Parent root = loader.load();
                IntrActvAgcController a = loader.getController();
                tlf.getScene().setRoot(root);
    }
    
}
