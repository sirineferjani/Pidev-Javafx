/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PaymentController implements Initializable {

     @FXML
    private HBox cardLayout;
      @FXML
    private Button Add;

    @FXML
    private TextField CIN;

    @FXML
    private DatePicker ExpirationDate;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Label menu;

    @FXML
    private Label menuClose;

    @FXML
    private TextField phoneNumber;
    Connection cnx;
    public PreparedStatement st;




    @FXML
    void Add(ActionEvent event) {
          String FirstName = firstName.getText();
        String LastName = lastName.getText();
        String cin = CIN.getText();
        String PhoneNumber = phoneNumber.getText();


        String sql = "insert into paiement(firstName , lastName , CIN , phoneNumber) values(?,?,?,?)";
        if (!FirstName.equals("") && !LastName.equals("") && !cin.equals("") && !PhoneNumber.equals("")) {
            try {
                st = cnx.prepareStatement(sql);
                st.setString(1, FirstName);
                st.setString(2, LastName);
                st.setString(3, cin);
                st.setString(4, PhoneNumber);
                st.execute();

                firstName.setText("");
                lastName.setText("");
                phoneNumber.setText("");
                CIN.setText("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Produit ajouté avec sucés", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
   
    
}
