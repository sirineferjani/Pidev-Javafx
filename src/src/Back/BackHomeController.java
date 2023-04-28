/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class BackHomeController implements Initializable {

      @FXML
    private Button AddBtn;

    @FXML
    private Button UpdateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TreeTableColumn<?, ?> image;

    @FXML
    private TreeTableColumn<?, ?> name;

    @FXML
    private TreeTableColumn<?, ?> price;

    @FXML
    private TreeTableColumn<?, ?> reference;

    @FXML
    private TreeTableColumn<?, ?> statut;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtReference;

    @FXML
    void Add(ActionEvent event) {

    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
