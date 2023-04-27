/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entitie.article;
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import service.articleService;

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
         afficherStatistiques();
    }    
   private void afficherStatistiques() {
    // Récupérer la liste des articles depuis une source de données
   articleService cs=new articleService();
        ObservableList<article>articles=FXCollections.observableArrayList();
        articles=cs.afficherArticle();     // récupérer la liste des articles

  int nbArticlesNoteFaible = 0;
int nbArticlesNoteMoyenne = 0;
int nbArticlesNoteExcellente = 0;

// Compter le nombre d'articles pour chaque catégorie de note
for (article article : articles) {
    float note = article.getNote();
    if (note < 2.0) {
        nbArticlesNoteFaible++;
    } else if (note < 3.0) {
        nbArticlesNoteMoyenne++;
    } else {
        nbArticlesNoteExcellente++;
    }
}

    // Créer une liste de données pour le camembert
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    int totalArticles = nbArticlesNoteFaible + nbArticlesNoteMoyenne + nbArticlesNoteExcellente;
   
    // Ajouter les données au diagramme circulaire
if (nbArticlesNoteFaible > 0) {
    double pourcentage = ((float)nbArticlesNoteFaible / totalArticles) * 100.0;
    pieChartData.add(new PieChart.Data("Note faible (" + nbArticlesNoteFaible + ") " + String.format("%.2f", pourcentage) + "%", nbArticlesNoteFaible));
}
if (nbArticlesNoteMoyenne > 0) {
    double pourcentage = ((float)nbArticlesNoteMoyenne / totalArticles) * 100.0;
    pieChartData.add(new PieChart.Data("Note moyenne (" + nbArticlesNoteMoyenne + ") " + String.format("%.2f", pourcentage) + "%", nbArticlesNoteMoyenne));
}
if (nbArticlesNoteExcellente > 0) {
    double pourcentage = ((float)nbArticlesNoteExcellente / totalArticles) * 100.0;
    pieChartData.add(new PieChart.Data("Note excellente (" + nbArticlesNoteExcellente + ") " + String.format("%.2f", pourcentage) + "%", nbArticlesNoteExcellente));
}
    // Créer et configurer le camembert
    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Statistiques des articles en fonction de leur note");

    // Afficher le camembert dans une nouvelle fenêtre
    Stage stage = new Stage();
    Scene scene = new Scene(new Group(chart), 600, 400);
    stage.setScene(scene);
    stage.show();
    System.out.println("Fenêtre affichée");
}

}