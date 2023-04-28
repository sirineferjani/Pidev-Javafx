/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class HomePageController implements Initializable {

    @FXML
    private Button activities;

    @FXML
    private Button backup;
    @FXML
    private Button backBtn;

    @FXML
    private Button basket;

    @FXML
    private Button bills;

    @FXML
    private Button contact;

    @FXML
    private Button dashboard;

    @FXML
    private Button event;

    @FXML
    private ImageView exit;

    @FXML
    private Button home;

    @FXML
    private Label menu;

    @FXML
    private Label menuClose;

    @FXML
    private Button payment;

    @FXML
    private Button products;

    @FXML
    private VBox slider;
     
    private Parent fxml ;
    @FXML
    private GridPane panierContainer;
         @FXML
    private Button statistic;

    @FXML
    void statistic(MouseEvent event) {
          try{
           fxml=FXMLLoader.load(getClass().getResource("/interfaces/Statistics.fxml"));
           slider.getChildren().removeAll();
           slider.getChildren().setAll(fxml);
           
       }catch(IOException e){
           e.printStackTrace();
       }
    }
    @FXML
    void back(MouseEvent event) {
          try{
           fxml=FXMLLoader.load(getClass().getResource("/Back/BackHome.fxml"));
           slider.getChildren().removeAll();
           slider.getChildren().setAll(fxml);
           
       }catch(IOException e){
           e.printStackTrace();
       }

    }
     @FXML
    void home(MouseEvent event) {
       try{
           fxml=FXMLLoader.load(getClass().getResource("/interfaces/HomePage.fxml"));
           slider.getChildren().removeAll();
           slider.getChildren().setAll(fxml);
           
       }catch(IOException e){
           e.printStackTrace();
       }
    }

    @FXML
    void basket(MouseEvent event) {
       try{
           fxml=FXMLLoader.load(getClass().getResource("/interfaces/Products.fxml"));
           slider.getChildren().removeAll();
           slider.getChildren().setAll(fxml);
           
       }catch(IOException e){
           e.printStackTrace();
       }
    }

    @FXML
    void bills(MouseEvent event) {

    }

    @FXML
    void contact(MouseEvent event) {

    }

    @FXML
    void payment(MouseEvent event) {
      try{
           fxml=FXMLLoader.load(getClass().getResource("/interfaces/Payment.fxml"));
           slider.getChildren().removeAll();
           slider.getChildren().setAll(fxml);
           
       }catch(IOException e){
           e.printStackTrace();
       }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        slider.setTranslateX(-176);
        exit.setOnMouseClicked(event->{
          System.exit(0);
        });
        menu.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            
            slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(false);
                menuClose.setVisible(true);
            });
        });
        
         menuClose.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();
            
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(true);
                menuClose.setVisible(false);
            });
        });
         
         
    }    
    
}
