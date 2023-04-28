/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Activite;
import entities.Agence;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import services.ServiceAc;
import services.ServiceAg;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class AffAgController implements Initializable {

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
    @FXML
    private TextField rec;
    @FXML
    private Button rt;
    
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
    private void search(ActionEvent event) throws IOException{
ServiceAg service = new ServiceAg();
List<Agence> list = service.afficherAg();
ObservableList<Agence> observableList = FXCollections.observableList(list);
ObservableList<Agence> filteredList = FXCollections.observableArrayList();
         filteredList.clear();
        for (Agence Skill : list) {
            if(String.valueOf(Skill.getNom()).contains(rec.getText())){
                filteredList.add(Skill);
            }
        }
   idagg.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomagg.setCellValueFactory(new PropertyValueFactory<>("nom"));
    nbra.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));
    deva.setCellValueFactory(new PropertyValueFactory<>("devis"));
    tlfa.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        
        affiche.setItems(filteredList);
    }

    @FXML
    private void rec(KeyEvent event) {
    }

    @FXML
    private void rtr(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IntFront.fxml"));
                Parent root = loader.load();
                IntFrontController a = loader.getController();
                rt.getScene().setRoot(root);
    }
    
}
