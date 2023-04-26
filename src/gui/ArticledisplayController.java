/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entitie.SMS;
import entitie.article;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import service.articleService;
import utils.Statics;
import java.util.stream.Collectors;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;



/**
 * FXML Controller class
 *
 * @author user
 */
public class ArticledisplayController implements Initializable {

    @FXML
    private FlowPane articlepane;
    ObservableList<article>listprod=FXCollections.observableArrayList();
    //List<article>listprod=new ArrayList();
    @FXML
    private TextField TFrechercheReca;
    public static final String ACCOUNT_SID = "ACdf205108665fa554493707ff38b480d9";
    public static final String AUTH_TOKEN = "fed36538b192dcc9d4bd0a9efe99ffa8";

    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            addtopane();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
         recherche();
        /*  articleService a=new articleService();
        
        listprod=a.afficherArticle();
        SMS ss=new SMS();
        for(article art:listprod){
            if(art.getStock()==0){
                ss.sms(art.getNom_article());
            }
        } */
    }    
  /*    public static void sendSms(String recipient, String messageBody) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(
            new PhoneNumber(recipient), // To number
            new PhoneNumber("+15672922126"), // From number
            messageBody) // SMS body
        .create();

    System.out.println("Message sent: " + message.getSid());
  }*/
    


    public void addtopane() throws FileNotFoundException{
       articlepane.getChildren().clear();
         articleService cs=new articleService();
        ObservableList<article>listart=FXCollections.observableArrayList();
        listart=cs.afficherArticle();    
        for(article art:listart){
                VBox card=new VBox();
                card.setPrefSize(150, 150);
                card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                ImageView imageView;
            try {
               imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+art.getImage())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Label namelabel=new Label(art.getNom_article());    
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Button btn=new Button("Modifier");
            btn.setAlignment(Pos.TOP_RIGHT);
            btn.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn.setOnAction(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateArticle.fxml"));
                    Parent root = loader.load();
                    //artegoryupdateController cuc=loader.getController();
                    //cuc.setData(art);
                    UpdateArticleController uac=loader.getController();
                            uac.recupdata(art);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Modifier artegorie");
                    Stage stage1 = (Stage) card.getScene().getWindow();
                    stage1.close();
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(UpdateArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            card.getChildren().add(btn);
            Button btn1=new Button("Supprimer");
            btn1.setAlignment(Pos.TOP_RIGHT);
             btn1.setStyle("-fx-background-color: #1372f4; -fx-background-radius: 25px; -fx-text-fill: white;");
            btn1.setOnAction(e->{
                articleService as=new articleService();
                as.supprimerArticle(art);
                    try {
                        addtopane();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
            card.getChildren().add(btn1);
            articlepane.getChildren().add(card);
            articlepane.setMargin(card, new Insets(5, 5, 5, 5));
     }}
    /*private Label createArticleNode(article article) 
    {
    Label label = new Label(article.getNom_article());
    return label;
    }*/
    
   /* public void verifierStockEtEnvoyerSMS(article a) {
        
    if (a.getStock() < 6) {
        sms(a.getNom_c);
    }
}*/

    private Node createArticleNode(article article) throws FileNotFoundException {
    // Créer un VBox pour contenir le nom et le prix de l'article
     if(article == null) {
        return null;
    }
     else{
    VBox articleBox = new VBox();
          articleBox.setPrefSize(150, 150);
                articleBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
                ImageView imageView;
            try {
               imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+article.getImage())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                articleBox.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }

    // Créer des labels pour le nom et le prix de l'article
      Label namelabel=new Label(article.getNom_article());    
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(namelabel);
            
            
    StackPane stackPane = new StackPane();
    stackPane.getChildren().addAll(articleBox);

    // Ajouter un style CSS au VBox pour qu'il soit bien présenté dans le FlowPane
    articleBox.setStyle("-fx-padding: 10px; -fx-background-color: #f2f2f2; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: #cccccc; -fx-border-width: 1px;");

    // Définir les contraintes de taille pour le VBox et l'ImageView
    articleBox.setPrefWidth(150);
    articleBox.setMaxWidth(150);
    articlepane.getChildren().add(articleBox);
    articlepane.setMargin(articleBox, new Insets(5, 5, 5, 5));

    // Retourner le StackPane contenant l'ImageView et le VBox
    return stackPane;
     }
}
   /* @FXML
    private void recherche(ActionEvent event) {
          // Ajouter un listener sur le champ de recherche pour effectuer la recherche à chaque modification du texte
    TFrechercheReca.textProperty().addListener((observable, oldValue, newValue) -> {
       articleService sp=new articleService();   
        // Filtrer les réclamations en utilisant le nouveau texte de recherche
       List<article> articlerecherche = sp.afficherArticle().stream()
        .filter(article -> 
            article.getNom_article().toLowerCase().contains(newValue.toLowerCase()) 
         
        )
        .collect(Collectors.toList());
         // Vider le FlowPane actuel pour afficher les articles filtrés
        articlepane.getChildren().clear();
         for (article article : articlerecherche) {
            Node articleNode = null;
           try {
               articleNode = createArticleNode(article);
           } catch (FileNotFoundException ex) {
               Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
           }
            articlepane.getChildren().add(articleNode);
              if(articleNode != null) {
            articlepane.getChildren().add(articleNode); // ajouter le nouveau noeud dans le FlowPane
        }
        }
        articlepane.layout();
        // Mettre à jour la TableView avec les réclamations filtrées
       // articlepane.setItems(FXCollections.observableArrayList(articlerecherche));
    });
        
    }*/
private void recherche() {
    // Ajouter un listener sur le champ de recherche pour effectuer la recherche à chaque modification du texte
    TFrechercheReca.textProperty().addListener((observable, oldValue, newValue) -> {
        articleService sp=new articleService();   
        // Filtrer les articles en utilisant le nouveau texte de recherche
        List<article> articlerecherche = sp.afficherArticle().stream()
                .filter(article -> article.getNom_article().toLowerCase().contains(newValue.toLowerCase()))
                .collect(Collectors.toList());
        // Vider le FlowPane actuel pour afficher les articles filtrés
        articlepane.getChildren().clear();
        for (article article : articlerecherche) {
            try {
                Node articleNode = createArticleNode(article);
                if (articleNode != null) {
                    articlepane.getChildren().add(articleNode); // ajouter le nouveau noeud dans le FlowPane
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArticledisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        articlepane.layout();
    });
}


   
    
   

    
}
