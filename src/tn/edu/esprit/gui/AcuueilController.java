/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AcuueilController implements Initializable {

    @FXML
    private Button btnliste;
    @FXML
    private VBox root;

    @FXML
    void AllerVersListe(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accueil(MouseEvent event) {
    }

    @FXML
    private void articles(MouseEvent event) {
    }

    @FXML
    private void Paiement(MouseEvent event) {
    }

    @FXML
    private void Factures(MouseEvent event) {
    }

    @FXML
    private void Historiques(MouseEvent event) {
    }
    
}
