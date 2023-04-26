/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatController implements Initializable {

     private Statement st;
    private ResultSet rs;
    private Connection cnx;
        ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @FXML
    private PieChart piedchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         stat();
    }    
private void stat() {
    
        // Création du graphique
        PieChart pieChart = new PieChart(data);
        pieChart.setTitle("Statistiques nombres des catégories");
        pieChart.setLegendSide(Side.LEFT);
        

        // Création d'une pile pour afficher le graphique
        StackPane root = new StackPane();
        root.getChildren().add(pieChart);

        // Création de la scène
        Scene scene = new Scene(root, 600, 400);

        // Affichage de la scène

    }

    private void loadData() throws SQLException {
        String query = "SELECT c.categorie, COUNT(a.id) AS nb_article "
                     + "FROM categorie c "
                     + "LEFT JOIN article a ON c.id = a.id_categorie "
                     + "GROUP BY c.categorie";
        PreparedStatement stmt = cnx.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String category = rs.getString("categorie");
            int count = rs.getInt("nb_article");
            data.add(new PieChart.Data(category, count));
        }
    }

}