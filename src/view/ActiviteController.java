/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Activite;

import entities.Upload;
import java.io.File;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import services.ServiceAc;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class ActiviteController implements Initializable {

    private File file;
    String pic;
    private TextField idac;
    @FXML
    private TextField nomac;
    @FXML
    private TextField desc;
    @FXML
    private TextField age;
    @FXML
    private TextField lieu;
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
    @FXML
    private Button btnimage;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    private Activite activite;
    
    private int nvId;
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
    private void supp(ActionEvent event) throws IOException {
        
        ServiceAc sm = new ServiceAc();        
        
        StringBuilder errors = new StringBuilder();
        
        if (this.nvId==0) {
            errors.append("Please enter an id\n");
        }
        
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        
        sm.supprimerAc(this.nvId);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Cours is deleted successfully!");
        alert.show();        
        
        refreshTable();
    }
    
    @FXML
    private void insert(ActionEvent event) throws IOException {
        ServiceAc sm = new ServiceAc();        
        Activite c = new Activite();
        
        StringBuilder errors = new StringBuilder();
        
        if (nomac.getText().trim().isEmpty() && desc.getText().trim().isEmpty()) {
            errors.append("svp entrer un nom et la description\n");
        }
        if (nomac.getText().trim().isEmpty() || nomac.getText().length() < 3 || nomac.getText().length() > 9) {
            errors.append("Please enter a name between 4 and 9 characters\n");
        }
        if (desc.getText().trim().isEmpty() || desc.getText().length() < 6 || desc.getText().length() > 100) {
            errors.append("Please enter a description superior 6 characters\n");
        }
        
        if (lieu.getText().trim().isEmpty() || lieu.getText().length() < 2 || lieu.getText().length() > 12) {
            errors.append("Please enter a lieu between 3 and 12 characters\n");
        }
        
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Activite is added successfully!");
            alert.show();            
            
            c.setNom(nomac.getText());
            c.setAge(Integer.parseInt(age.getText()));
            c.setDescription(desc.getText());
            c.setLieu(lieu.getText());
            c.setImage(pic);
            
            sm.ajouterAc(c);
            
            refreshTable();
            
            nomac.setText("");
            age.setText("");
            desc.setText("");
            lieu.setText("");
            
        }
    }
    @FXML
    private void modifier(ActionEvent event) throws IOException, ParseException {
        
        Activite c = new Activite();
        StringBuilder errors = new StringBuilder();
        
         if (this.nvId==0) {
            errors.append("Please enter an id\n");
        }
        
        // Vérifier si le champ nom est vide ou a une taille incorrecte
        if (nomac.getText().trim().isEmpty() || nomac.getText().length() < 3 || nomac.getText().length() > 9) {
            errors.append("Please enter a name between 4 and 9 characters\n");
        }
        
        if (desc.getText().trim().isEmpty() || desc.getText().length() < 6 || desc.getText().length() > 100) {
            errors.append("Please enter a description superior 6 characters\n");
        }
        
        if (lieu.getText().trim().isEmpty() || lieu.getText().length() < 2 || lieu.getText().length() > 12) {
            errors.append("Please enter a lieu between 3 and 12 characters\n");
        }
        
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else { // Si la validation est réussie, effectuer la modification
            ServiceAc sm = new ServiceAc();            
            
            c.setId(this.nvId);            
            c.setNom(nomac.getText());
            
            c.setAge(Integer.parseInt(age.getText()));
            c.setDescription(desc.getText());
            c.setLieu(lieu.getText());
            if(pic==null)
            {
                            c.setImage(activite.getImage());

            }else{
                                            c.setImage(pic);

            }
            
            sm.modiferAc(c);
            
            refreshTable();
            
            nomac.setText("");
            age.setText("");
            desc.setText("");
            lieu.setText("");
            pic="";
        }
    }
    
    private void rtr(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IntrActvAgc.fxml"));
        Parent root = loader.load();
        IntrActvAgcController a = loader.getController();
        age.getScene().setRoot(root);
    }
    
    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
        
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //pic=(file.toURI().toString());
        pic = new Upload().upload(file, "\\img");
        System.out.println(pic);
    }
    
    
   
    @FXML
    private void onTableItemSelect(MouseEvent event) {
        
        activite = affiche.getSelectionModel().getSelectedItem();        
        nomac.setText(activite.getNom());
        desc.setText(activite.getDescription());
        age.setText(String.valueOf(activite.getAge()));
        lieu.setText(activite.getLieu());
        this.nvId=activite.getId();
    }
    
}
