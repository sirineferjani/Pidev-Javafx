/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Products;
import tn.edu.esprit.tests.FXMain;
import tn.edu.esprit.tests.Listener;

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
    private VBox ChosenProductCard;
      @FXML
    private VBox content;
     
    @FXML
    private Button btnAdd;
    @FXML
    private Label description;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView imageProduct;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private ScrollPane scroll;
      @FXML
    private TextField tfSearchName;
    @FXML
    private TextField total;
    private Image image ;
    private Listener myListener;
    private List<Products> products = new ArrayList();
    private List<Products> getData(){
          List<Products> products = new ArrayList();
         Products product;
        
             product =new Products();
             product.setName("Tente");
             product.setDescription("Tente Camping");
             product.setPrice(150);
             product.setImageSrc("/image/te-removebg-preview.png");
             products.add(product);
             
             product =new Products();
             product.setName("chaise");
             product.setDescription("Chaise de Camping");
             product.setPrice(150);
             product.setImageSrc("/image/kn-removebg-preview.png");
             products.add(product);
             
             product =new Products();
             product.setName("Sac");
             product.setDescription("Sac Impermeable de Camping");
             product.setPrice(150);
             product.setImageSrc("/image/lj-removebg-preview.png");
             products.add(product);
             
        
             
             product =new Products();
             product.setName("Lit");
             product.setDescription("Lit de Camping ");
             product.setPrice(150);
             product.setImageSrc("/image/sdc-removebg-preview.png");
             products.add(product);
             
             product =new Products();
             product.setName("Cabine de Douche");
             product.setDescription("CABINE DE DOUCHE POUR LE CAMPING");
             product.setPrice(150);
             product.setImageSrc("/image/ln7-removebg-preview.png");
             products.add(product);
             
             product =new Products();
             product.setName("Tabel");
             product.setDescription("TABLE DE CAMPING PLIANTE");
             product.setPrice(150);
             product.setImageSrc("/image/ldcdkc-removebg-preview.png");
             products.add(product);
        
         return products;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products.addAll(getData());
        if(products.size() >0){
            chosenProduct(products.get(0));
              
            myListener = new Listener(){
                

                @Override
                public void onClickListener(Products product) {
                       chosenProduct(product);
                }
            };
        }
        int column = 0;
        int row = 0;
         try{
        for(int i =0 ; i<products.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/interfaces/Card.fxml"));
           
                 AnchorPane anchorpane = fxmlLoader.load();

            CardController cardController =fxmlLoader.getController();
            cardController.setData(products.get(i), myListener);
            if(column ==3){
                column = 0;
                row ++ ; 
            }
                grid.add(anchorpane , column++ , row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);
                
                
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);

                
                GridPane.setMargin(anchorpane , new Insets(10));
        }
            }catch(IOException e){
                e.printStackTrace();
            }
            
        }
    
    private void chosenProduct(Products product){
        name.setText(product.getName());
        description.setText(product.getDescription());
        price.setText(FXMain.CURRENCY + product.getPrice());
         image = new Image(getClass().getResourceAsStream(product.getImageSrc()));
         imageProduct.setImage(image);
         ChosenProductCard.setStyle("-fx-background-color :#F16C31;\n"+ "-fx-background-radius : 30;");
        
    }
    
    
    
     @FXML
    void AddToCart(ActionEvent event) {
              try{
          Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Panier.fxml"));
         
          
          Scene scene = new Scene(root);
          Stage stage = new Stage();
          stage.setScene(scene);
          stage.show();
                  
         }catch(IOException e){
          System.out.append("erreur d'affichage");
          e.printStackTrace();
       }
    }
    
  
 }    

    
    
    
    
    
    
    
    

