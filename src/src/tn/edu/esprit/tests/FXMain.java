/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author abdelazizmezri
 */
public class FXMain extends Application {
    public static final String  CURRENCY = "$";
    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
        Parent  root = FXMLLoader.load(getClass().getResource("/interfaces/HomePage.fxml"));
         Scene scene = new Scene(root , 900 , 900);
        
       
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion des produits");
        primaryStage.show();
        primaryStage.setResizable(false);
        }catch(Exception e){
      e.printStackTrace();
}
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
