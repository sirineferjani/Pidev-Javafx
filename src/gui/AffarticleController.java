/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.article;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import service.articleService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffarticleController implements Initializable {

    @FXML
    private TableView<article> tablearticle;
    @FXML
    private TableColumn<Integer, article> tfref;
    @FXML
    private TableColumn<String, article> tfnom;
    @FXML
    private TableColumn<String, article> tfdes;
    @FXML
    private TableColumn<Integer, article> tfprix;
    @FXML
    private TableColumn<String, article> tfimage;
    @FXML
    private TableColumn<Integer, article> tfs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        articleService as = new articleService();
        List<article> article = as.afficherArticle();
        ObservableList<article> obs = FXCollections.observableArrayList(article);
        tablearticle.setItems(obs);
        tfref.setCellValueFactory(new PropertyValueFactory<>("ref_article"));
        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom_article"));
        tfdes.setCellValueFactory(new PropertyValueFactory<>("description"));
        tfprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tfimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tfs.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        // obs.remove()
        //obs.add()
    }    
    
    
    };
    
