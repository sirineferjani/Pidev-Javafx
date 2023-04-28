/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class IntFrontController implements Initializable {

    @FXML
    private Button act;
    @FXML
    private Button agc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ac(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/affAct.fxml"));
                Parent root = loader.load();
                AffActController af = loader.getController();
                act.getScene().setRoot(root);
    }

    @FXML
    private void ag(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/affAg.fxml"));
                Parent root = loader.load();
                AffAgController a = loader.getController();
                agc.getScene().setRoot(root);
    }
    
}
