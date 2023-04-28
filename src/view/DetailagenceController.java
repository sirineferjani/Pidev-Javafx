/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package view;

import entities.Activite;
import entities.Agence;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.ServiceAc;
import services.ServiceAg;
import utils.MyDB;
import static view.OneActiviteController.idActivite;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DetailagenceController implements Initializable {

    public static int idUsr = 1;

    @FXML
    private Text nom;
    @FXML
    private Text nbr_p;
    @FXML
    private Text devis;
    @FXML
    private Text num_tel;
    @FXML
    private Text idAct;
    @FXML
    private ImageView imgev;

    private boolean etat;
    private int nbrp;
    private int idev;

    Connection cnx = MyDB.getInstance().getcnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
loadData();
    }

    @FXML
    private void PDF(ActionEvent event) {
    }

    public void ReadById(int id) {

    }

    public void loadData() {
        Agence ag = new Agence();
        ServiceAg sg = new ServiceAg();

        ag = sg.ReadByNVid(idActivite);
        nom.setText(ag.getNom());
        nbr_p.setText(String.valueOf(ag.getNbr_participant()));
        devis.setText(String.valueOf(ag.getDevis()));
        idAct.setText(String.valueOf(ag.getActivite_id()));


        num_tel.setText(String.valueOf(ag.getNum_tel()));


    }
}
