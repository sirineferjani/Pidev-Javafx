/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Products;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ProductsController implements Initializable {

    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button CloseButton;

    @FXML
    private TableView<Products> table;

    @FXML
    private TableColumn<Products, Integer> Id;
    @FXML
    private TableColumn<Products, String> Name;
    @FXML
    private TableColumn<Products, String> Description;

    @FXML
    private TableColumn<Products, Double> Price;

    @FXML
    private TableColumn<Products, String> Statut;
    @FXML
    private TextField tfSearchName;
    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfName;

    @FXML
    private Button searchbtn;

    @FXML
    private TextField tfPrice;

    @FXML
    private TextField tfStatut;

    @FXML
    public void CloseButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Add(ActionEvent event) {
        String name = tfName.getText();
        String description = tfDescription.getText();
        String statut = tfStatut.getText();
        String price = tfPrice.getText();

        String sql = "insert into Products(name , description , statut , price) values(?,?,?,?)";
        if (!name.equals("") && !description.equals("") && !statut.equals("") && !price.equals("")) {
            try {
                st = cnx.prepareStatement(sql);
                st.setString(1, name);
                st.setString(2, description);
                st.setString(3, statut);
                st.setString(4, price);
                st.execute();

                tfName.setText("");
                tfDescription.setText("");
                tfStatut.setText("");
                tfPrice.setText("");

                tfSearchName.setText("");
                Alert alert = new Alert(AlertType.CONFIRMATION, "Produit ajouté avec sucés", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
                showProducts();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    public void table() {

    }

    @FXML
    void Delete(ActionEvent event) {
        String sql = "delete from Products  where name = '" + tfSearchName.getText() + "'";
        try {
            st = cnx.prepareStatement(sql);
            st.executeUpdate();

            tfName.setText("");
            tfDescription.setText("");
            tfStatut.setText("");
            tfPrice.setText("");

            tfSearchName.setText("");
            Alert alert = new Alert(AlertType.CONFIRMATION, "Produit supprimé avec sucés", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            showProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        
    }
        

}
     @FXML
    void tableProductsEvent() {
      Products products =table.getSelectionModel().getSelectedItem();
      String sql = "select * from products where id = ?";
      try{
           st=cnx.prepareStatement(sql);
           st.setInt(1, products.getId());
            result=st.executeQuery();
            if(result.next()){
                tfName.setText(result.getString("name"));
                tfDescription.setText(result.getString("description"));
                tfStatut.setText(result.getString("statut"));
                tfPrice.setText(result.getString("price"));
                tfSearchName.setText(result.getString("name"));
                


            }
        }catch(SQLException e){
            e.printStackTrace();    
        
      }
    }


@FXML
    void Update() {
       String name=tfName.getText();
       String description=tfDescription.getText();
       String statut=tfStatut.getText();
       String price=tfPrice.getText();
    
       String sql ="update Products set name=? , description=? , statut=? , price=? where name = '"+tfSearchName.getText()+"'";
        if(!name.equals("")&&!description.equals("")&&!statut.equals("")&&!price.equals("")){

        try{
            st=cnx.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setString(3, statut);
            st.setString(4, price);
            st.execute();

          tfName.setText("");
          tfDescription.setText("");
          tfStatut.setText("");
          tfPrice.setText("");

          tfSearchName.setText("");
          Alert alert = new Alert(AlertType.CONFIRMATION,"Produit modifié avec sucés", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
          showProducts();
        }catch(SQLException e){
            e.printStackTrace();    
        }
           
       }else{
            Alert alert = new Alert(AlertType.WARNING,"Veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
          alert.showAndWait();
       
        }
    }

    void saveProducts(ActionEvent event) {

    }

    @Override
        public void initialize(URL url, ResourceBundle rb) {
        cnx = ConnexionMysql.connexionDB();
        showProducts();
    }
    
    @FXML
    void SearchProducts() {
        String sql="select id , name , description ,statut , price from products where name = '"+tfSearchName.getText()+"'";
        int m=0;
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            if(result.next()){
                tfName.setText(result.getString("name"));
                tfDescription.setText(result.getString("description"));
                tfStatut.setText(result.getString("statut"));
                tfPrice.setText(result.getString("price"));
                m=1;


            }
        }catch(SQLException e){
            e.printStackTrace();    
        }

    
    if(m==0){
     Alert alert= new Alert(AlertType.ERROR, "Aucun Produit trouvé avec name ="+tfSearchName.getText()+"", ButtonType.OK);
      alert.showAndWait();
  }

}

    ObservableList<Products> products = FXCollections.observableArrayList();

    public void showProducts() {
        table.getItems().clear();
        String sql="select * from products";
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while (result.next()){
                products.add(new Products(result.getInt("id"),result.getString("name"), result.getString("description"),  result.getString("statut"),result.getDouble("price")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        Id.setCellValueFactory(new PropertyValueFactory<Products,Integer>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Products,String>("name"));
        Description.setCellValueFactory(new PropertyValueFactory<Products,String>("description"));
        Price.setCellValueFactory(new PropertyValueFactory<Products,Double>("price"));
        Statut.setCellValueFactory(new PropertyValueFactory<Products,String>("statut"));
        table.setItems(products);
    }

    @FXML
    private void Contact(MouseEvent event) {
    }

}
