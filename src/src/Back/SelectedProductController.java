/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.edu.esprit.entities.Products;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class SelectedProductController implements Initializable {

    
    @FXML
    private Button AddBtn;

    @FXML
    private Button UpdateBtn;

    @FXML
    private Button deleteBtn;
     @FXML
    private TreeTableView<?> table;
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
       String name = txtName.getText();
        String reference = txtReference.getText();
        String price = txtPrice.getText();

        String sql = "insert into Products(name , description , statut , price) values(?,?,?,?)";
        if (!name.equals("") && !reference.equals("") && !statut.equals("") && !price.equals("")) {
            try {
                st = cnx.prepareStatement(sql);
                st.setString(1, name);
                st.setString(2, reference);
                st.setString(4, price);
                st.execute();

                txtName.setText("");
                txtReference.setText("");
                txtPrice.setText("");

               
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Produit ajouté avec sucés", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
                showProducts();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }

    }
    public void showProducts() {
     
    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }
   Connection cnx ;
   PreparedStatement st ;
   int myIndex;
   

    public Connection connect() {
        String USER = "root";
        String databaseName="pidev";
        String PASSWORD = "";
        String URL = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.jbdc.Driver");
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cnx;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
