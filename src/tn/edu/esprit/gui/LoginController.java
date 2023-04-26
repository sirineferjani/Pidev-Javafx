/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import tn.edu.esprit.entities.user;
import tn.edu.esprit.services.ServicePersonne;

/**
 * FXML Controller class
 *
 * @author nourb
 */
public class LoginController implements Initializable {

    @FXML
    private TextField emaillogin;
    @FXML
    private PasswordField pwdlogin;

    private ServicePersonne service = new ServicePersonne();

    private int numFailedLoginAttempts = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    //2FA mail 
/*@FXML
public void handleLogin(ActionEvent event) throws SQLException, Exception {
    try {
        boolean result = service.login(emaillogin.getText(), pwdlogin.getText());
        if (result) {
            user u = service.getUserByEmail2(emaillogin.getText());
            
            // Générer un code aléatoire à 6 chiffres
            Random rand = new Random();
            int code = rand.nextInt(900000) + 100000;
            
            // Envoyer le code par email
            String recipient = u.getEmail();
            String subject = "Code de vérification pour accéder à votre compte";
            String messageText = "Bonjour " + u.getNom() + ",\n\nVoici votre code de vérification pour accéder à votre compte : " + code + "\n\nCordialement,\nL'équipe de l'application";
            Sendmail(recipient, subject, messageText , code);
            
            // Ouvrir la boîte de dialogue pour entrer le code
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Vérification de sécurité");
            dialog.setHeaderText("Un code de vérification a été envoyé à votre adresse email. Veuillez entrer le code ci-dessous pour accéder à votre compte.");
            dialog.setContentText("Code :");

            // Attendre la saisie du code
            Optional<String> resultDialog = dialog.showAndWait();
            
           if (resultDialog.isPresent() && Integer.parseInt(resultDialog.get()) == code) {
 
                boolean loginSuccess = true;
                if (u.getRole().equals("Admin")) {
                    Parent root = FXMLLoader.load(getClass().getResource("adminback.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else if (u.getRole().equals("User")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("profileuser.fxml"));
                    Parent root = loader.load();
                    ProfileuserController controller = loader.getController();
                    controller.setUserData(u);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    loginSuccess = false;
                }
                if (!loginSuccess) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de connexion");
                    alert.setContentText("Identifiant ou mot de passe incorrect");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de vérification");
                alert.setContentText("Le code de vérification est incorrect. Veuillez réessayer.");
                alert.showAndWait();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}*/
    //tentatives     
/*@FXML
public void handleLogin(ActionEvent event) throws SQLException {
    try {
        boolean result = service.login(emaillogin.getText(), pwdlogin.getText());
        if (result) {
            user u = service.getUserByEmail2(emaillogin.getText());
            if(u.getRole().equals("Admin")) {
            Parent root = FXMLLoader.load(getClass().getResource("adminback.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            }
            else if(u.getRole().equals("User")) {
                if (!service.isBanned(u)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("profileuser.fxml"));
                    Parent root = loader.load();
                    ProfileuserController controller = loader.getController();
                    controller.setUserData(u);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    // Afficher une boîte de dialogue indiquant que l'utilisateur est bloqué
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Utilisateur bloqué");
                    alert.setHeaderText("Votre compte a été bloqué !");
                    alert.setContentText("Vous ne pouvez pas vous connecter pour le moment. Veuillez réessayer plus tard.");
                    alert.showAndWait();
                }
            } 
            numFailedLoginAttempts = 0;

        } else {
           
            // Block user for 5 minutes after 3 failed login attempts
            numFailedLoginAttempts++;
            if (numFailedLoginAttempts >= 3) {
                service.banSelonDuree(service.getUserByEmail2(emaillogin.getText()), 5);
                numFailedLoginAttempts = 0;
                // Afficher une boîte de dialogue indiquant que l'utilisateur doit attendre avant de se reconnecter
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Tentatives de connexion infructueuses");
                alert.setHeaderText("Trop de tentatives de connexion infructueuses !");
                alert.setContentText("Vous devez attendre 5 minutes avant de tenter de vous reconnecter.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setContentText("Identifiant ou mot de passe incorrect");
                alert.showAndWait();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
     */
    @FXML
    public void handleLogin(ActionEvent event) throws SQLException, Exception {
        try {
            boolean result = service.login(emaillogin.getText(), pwdlogin.getText());
            if (result) {
                user u = service.getUserByEmail2(emaillogin.getText());

                // Générer un code aléatoire à 6 chiffres
                Random rand = new Random();
                int code = rand.nextInt(900000) + 100000;

                // Envoyer le code par email
                String recipient = u.getEmail();
                String subject = "Code de vérification pour accéder à votre compte";
                String messageText = "Bonjour " + u.getNom() + ",\n\nVoici votre code de vérification pour accéder à votre compte : " + code + "\n\nCordialement,\nL'équipe de l'application";
                Sendmail(recipient, subject, messageText, code);

                // Ouvrir la boîte de dialogue pour entrer le code
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Vérification de sécurité");
                dialog.setHeaderText("Un code de vérification a été envoyé à votre adresse email. Veuillez entrer le code ci-dessous pour accéder à votre compte.");
                dialog.setContentText("Code :");

                // Attendre la saisie du code
                Optional<String> resultDialog = dialog.showAndWait();

                if (resultDialog.isPresent() && Integer.parseInt(resultDialog.get()) == code) {

                    boolean loginSuccess = true;
                    if (u.getRole().equals("Admin")) {
                        Parent root = FXMLLoader.load(getClass().getResource("adminback.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else if (u.getRole().equals("User")) {
                        if (!service.isBanned(u)) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("profileuser.fxml"));
                            Parent root = loader.load();
                            ProfileuserController controller = loader.getController();
                            controller.setUserData(u);
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else {
                            // Afficher une boîte de dialogue indiquant que l'utilisateur est bloqué
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Utilisateur bloqué");
                            alert.setHeaderText("Votre compte a été bloqué !");
                            alert.setContentText("Vous ne pouvez pas vous connecter pour le moment. Veuillez réessayer plus tard.");
                            alert.showAndWait();
                        }
                    } else {
                        loginSuccess = false;
                    }
                    if (!loginSuccess) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de connexion");
                        alert.setContentText("Identifiant ou mot de passe incorrect");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de vérification");
                    alert.setContentText("Le code de vérification est incorrect. Veuillez réessayer.");
                    alert.showAndWait();
                }
            } else {
                // Bloquer le user pendant 5minutes après 3 attempts
                numFailedLoginAttempts++;
                if (numFailedLoginAttempts >= 3) {
                    service.banSelonDuree(service.getUserByEmail2(emaillogin.getText()), 5);
                    numFailedLoginAttempts = 0;
                    // Afficher une boîte de dialogue indiquant que l'utilisateur doit attendre avant de se reconnecter
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Tentatives de connexion infructueuses");
                    alert.setHeaderText("Trop de tentatives de connexion infructueuses !");
                    alert.setContentText("Vous devez attendre 5 minutes avant de tenter de vous reconnecter.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de connexion");
                    alert.setContentText("Identifiant ou mot de passe incorrect");
                    alert.showAndWait();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Sendmail(String recipient, String subject, String messageText, int code) throws Exception {
        Properties prop = new Properties();
        final String moncompteEmail = "nour.baatour123@gmail.com";
        final String psw = "qgaflrxtueqbkaky";
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");

        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nour.baatour123@gmail.com", "qgaflrxtueqbkaky");
            }
        });

        try {
            Message message = new MimeMessage(ses);
            message.setFrom(new InternetAddress("nour.baatour123@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageText);

            // Envoyer le message
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
