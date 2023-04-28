/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Graphics;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import tn.edu.esprit.tests.FXMain;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sun.security.tools.keytool.Main;
import tn.edu.esprit.entities.Products;
import tn.edu.esprit.tests.Listener;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CardController  {
       @FXML
    private ImageView imageProduct;
        @FXML
    private Label description;

    @FXML
    private Label name;
      @FXML
    private VBox content;
    
    @FXML
    private Label price;
    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(product);
    }
    private Products product;
    private Listener myListener;
   public void setData(Products product , Listener myListener){
       this.product = product ;
       this.myListener = myListener ;
       name.setText(product.getName());
       description.setText(product.getDescription());
       price.setText(FXMain.CURRENCY + product.getPrice());
       
      Image img = new Image(getClass().getResourceAsStream(product.getImageSrc()));
      imageProduct.setImage(img);
   }
    
}
 