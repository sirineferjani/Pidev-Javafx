import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CardView extends VBox {

    public CardView(String nom, String email, Image image) {
        super();

        // Créer un ImageView pour afficher l'image de l'utilisateur
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        // Créer un Text pour afficher le nom de l'utilisateur
        Text nomText = new Text(nom);
        nomText.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        // Créer un Text pour afficher l'email de l'utilisateur
        Text emailText = new Text(email);
        emailText.setStyle("-fx-font-size: 14;");

        // Ajouter les éléments à la CardView
        getChildren().addAll(imageView, nomText, emailText);
        setSpacing(10);
        setStyle("-fx-background-color: #f7f7f7; -fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: #e0e0e0;");
    }
}
