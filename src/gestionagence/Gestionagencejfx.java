/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionagence;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Amirov
 */
public class Gestionagencejfx extends Application {
   @Override
   public void start(Stage stage)throws Exception {
        
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("/view/IntrActvAgc.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/view/AllAct3.fxml"));
                    Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Activité");
        stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}