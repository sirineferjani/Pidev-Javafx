/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package view;

import entities.Activite;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OneActiviteController implements Initializable {

    @FXML
    private ImageView imgEvent;
    @FXML
    private Text NomAct;
    @FXML
    private Text AgeAct;
    @FXML
    private Text LieuAct;
    @FXML
    private Text descAct;

    public static int idActivite;
    
    private  Activite Activite;
    
    private MyListener myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void detailEvent(ActionEvent event) {
        // load the new scene
       
    }
     public void setData(Activite Activite,  MyListener myListener) throws MalformedURLException
    {
        this.myListener = myListener;
        this.Activite=Activite;

        NomAct.setText(Activite.getNom());
            AgeAct.setText(Activite.getNom());
                LieuAct.setText(Activite.getLieu());
                    descAct.setText(Activite.getDescription());
//get image from root directory and databse
        
        String path = "file:///C:/Users/computer/Desktop/ines/Gestionagence/src/img/"+Activite.getImage();
        Image image = new Image(path);
        System.out.println(path);
                System.out.println(image);

        imgEvent.setImage(image);


    }

    @FXML
    private void EventDetail(MouseEvent event) throws IOException {
        
         idActivite=Activite.getId();

       
        Parent root = FXMLLoader.load(getClass().getResource("detailagence.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
    }
    
        public interface MyListener {
    public void onClickListener(Activite Activite);
}
    
}
