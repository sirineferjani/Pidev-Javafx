/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Activite;
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


/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class AffActController implements Initializable {

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
    @FXML
    private TextField rec;
    @FXML
    private Button rt;

    
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
    private void rec(KeyEvent event) {
    }
    
    @FXML
    private void search(ActionEvent event) throws IOException{
ServiceAc service = new ServiceAc();
List<Activite> list = service.afficherAc();
ObservableList<Activite> observableList = FXCollections.observableList(list);
ObservableList<Activite> filteredList = FXCollections.observableArrayList();
         filteredList.clear();
        for (Activite Skill : list) {
            if(String.valueOf(Skill.getNom()).contains(rec.getText())){
                filteredList.add(Skill);
            }
        }
        ida.setCellValueFactory(new PropertyValueFactory<>("id"));
    noma.setCellValueFactory(new PropertyValueFactory<>("nom"));
    agea.setCellValueFactory(new PropertyValueFactory<>("age"));
    desca.setCellValueFactory(new PropertyValueFactory<>("description"));
    lieua.setCellValueFactory(new PropertyValueFactory<>("lieu"));
    imga.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        affiche.setItems(filteredList);
    }

    @FXML
    private void rtr(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IntFront.fxml"));
                Parent root = loader.load();
                IntFrontController a = loader.getController();
                rt.getScene().setRoot(root);
    }
    }
    

