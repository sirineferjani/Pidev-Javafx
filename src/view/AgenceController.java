/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entities.Agence;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import services.ServiceAg;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class AgenceController implements Initializable {

     
    private Agence Agence;
    public static int idAgence;
    @FXML
    private TextField idag;
    @FXML
    private TextField nomag;
    @FXML
    private TextField dev;
    @FXML
    private TextField nbr;
    @FXML
    private Button su;
    @FXML
    private Button aj;
    @FXML
    private Button mod;
    @FXML
    private TextField tlf;
    @FXML
    private TextField aid;
    @FXML
    private TableColumn<Agence, String> idagg;
        @FXML
    private TableColumn<Agence, String> nomagg;
    @FXML
    private TableColumn<Agence, String> nbra;
    @FXML
    private TableColumn<Agence, String> deva;
    @FXML
    private TableColumn<Agence, String> tlfa;
    @FXML
    private TableColumn<Agence, String> acta;
    @FXML
    private TableView<Agence> affiche;
    @FXML
    private TextField txtRecherche;
    @FXML
    private ComboBox<String> combo;
    ObservableList<Agence> obsAgence = FXCollections.observableArrayList();
    @FXML
    private AnchorPane cap;
    @FXML
    private Button PDF;

    
    
     public void refreshTable() {
    ServiceAg us = new ServiceAg();
    List<Agence> l = new ArrayList<>();
    l = (ArrayList<Agence>) us.afficherAg();
    ObservableList<Agence> data = FXCollections.observableArrayList(l);
    FilteredList<Agence> fle = new FilteredList(data, e -> true);
    idagg.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomagg.setCellValueFactory(new PropertyValueFactory<>("nom"));
    nbra.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));
    deva.setCellValueFactory(new PropertyValueFactory<>("devis"));
    tlfa.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
    acta.setCellValueFactory(new PropertyValueFactory<>("activite_id"));

    affiche.setItems(fle);
    
    
        combo.getItems().addAll("aucun", "Trier Selon Nom", "Trier Selon Telephone");

        affiche.setItems(data);

        txtRecherche.textProperty().addListener((obs, oldText, newText) -> {
            List<Agence> ae = us.Search(newText);
            affiche.getItems().setAll(ae);  
        });
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         refreshTable();
    }    

    @FXML
    private void supp(ActionEvent event) throws IOException {
        
         ServiceAg sm = new ServiceAg() ;   

            

      StringBuilder errors=new StringBuilder();
        
        if(idag.getText().trim().isEmpty()){
            errors.append("Please enter an id\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
                   sm.supprimerAg(Integer.parseInt(idag.getText()));

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is deleted successfully!");
            alert.show(); 
            
            refreshTable();
    }


    @FXML
    private void ajt(ActionEvent event) throws IOException {
        
        
         String filePath ="";
               try {
           //insert event info  and user name from  to qrcode 
            String qrCodeData = "Agence name :"+nomag.getText()+"\n"+"nu Tele :"+tlf+"\n"+"Number of places :"+nbr.getText();

                    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        // create a random number generator
            Random random = new Random();
        
        // generate a random name
        String name = "";
        for (int i = 0; i < 4; i++) {
            char c = alphabet[random.nextInt(alphabet.length)];
            name += c;
        }



             filePath = "C:\\Users\\computer\\Desktop\\ines\\Gestionagence\\src\\img\\"+name+".jpg";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
        
         ServiceAg sm = new ServiceAg() ;   
    Agence c = new Agence() ;
           
     
        StringBuilder errors=new StringBuilder();
        
        if(nomag.getText().trim().isEmpty()&&tlf.getText().trim().isEmpty()){
            errors.append("svp entrer un nom et le numero de telephone\n");
        }
        if(nomag.getText().trim().isEmpty() || nomag.getText().length() < 3 || nomag.getText().length() > 9){
        errors.append("Please enter a name between 4 and 9 characters\n");
    }
         if(tlf.getText().trim().isEmpty()) {
        errors.append("Please enter a Phone number.\n");
    } else {
        try {
            long num_tel = Long.parseLong(tlf.getText());
            if (num_tel < 0 || String.valueOf(num_tel).length() != 8) {
                errors.append("Phone number must be an 8 positive integer.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("The phone number must be an 8 integer.\n");
        }
    }
        
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {

            
      
      c.setNom(nomag.getText());
      c.setNbr_participant(Integer.parseInt(nbr.getText()));
      c.setDevis(Integer.parseInt(dev.getText()));
      c.setNum_tel(Integer.parseInt(tlf.getText()));
      c.setActivite_id(Integer.parseInt(aid.getText()));


         
            sm.ajouterAg(c);
                       
            refreshTable();
            // Réinitialisation des champs
        nomag.setText("");
        nbr.setText("");
        dev.setText("");
        tlf.setText("");
        aid.setText("");
        
             
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Activite is added successfully!");
            alert.show(); 
     }
    }

    @FXML
    private void modifier(ActionEvent event)throws IOException, ParseException {
        
        
    Agence c = new Agence();
    StringBuilder errors = new StringBuilder();
        
    if(idag.getText().trim().isEmpty()){
        errors.append("Please enter an id\n");
    }
        
    // Vérifier si le champ nom est vide ou a une taille incorrecte
    if(nomag.getText().trim().isEmpty() || nomag.getText().length() < 3 || nomag.getText().length() > 9){
        errors.append("Please enter a name between 4 and 9 characters\n");
    }
          if(tlf.getText().trim().isEmpty()) {
        errors.append("Please enter a Phone number.\n");
    } else {
        try {
            long num_tel = Long.parseLong(tlf.getText());
            if (num_tel < 0 || String.valueOf(num_tel).length() != 8) {
                errors.append("Phone number must be an 8 positive integer.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("The phone number must be an 8 integer.\n");
        }
    }
        
     
    if(errors.length() > 0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else { // Si la validation est réussie, effectuer la modification
        ServiceAg sm = new ServiceAg() ;  
         
        c.setId(Integer.parseInt(idag.getText()));   
       c.setNom(nomag.getText());
      c.setNbr_participant(Integer.parseInt(nbr.getText()));
      c.setDevis(Integer.parseInt(dev.getText()));
      c.setNum_tel(Integer.parseInt(tlf.getText()));
      //c.setActivite_id(Integer.parseInt(aid.getText()));

        
        sm.modiferAg(c);
        
        refreshTable();
        
         nomag.setText("");
        nbr.setText("");
        dev.setText("");
        tlf.setText("");
        aid.setText("");
    }
}

    @FXML
    private void rtr(ActionEvent event) throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IntrActvAgc.fxml"));
                Parent root = loader.load();
                IntrActvAgcController a = loader.getController();
                tlf.getScene().setRoot(root);
    }
    
     private void TrieNom() throws IOException {
        ServiceAg ums = new ServiceAg();
        Agence Panier = new Agence();
        List<Agence> a = ums.triNom();
        idagg.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomagg.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nbra.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));
        deva.setCellValueFactory(new PropertyValueFactory<>("devis"));
        tlfa.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        acta.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        affiche.getItems().setAll(a);

    }

    private void TrieQuantity() throws IOException {
        ServiceAg ums = new ServiceAg();
        Agence Panier = new Agence();
        List<Agence> a = ums.triQuantity();
        idagg.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomagg.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nbra.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));
        deva.setCellValueFactory(new PropertyValueFactory<>("devis"));
        tlfa.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        acta.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        affiche.getItems().setAll(a);
    }
    
    @FXML
    private void triCoice(ActionEvent event) throws IOException {
        if (combo.getValue().equals("Trier Selon Nom")) {
            TrieNom();
        } else if (combo.getValue().equals("Trier Selon Telephone")) {
            TrieQuantity();
        }   
    }

    @FXML
    private void PDF(ActionEvent event) {
          PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
             Window primaryStage = null;
             job.showPrintDialog(primaryStage); 
            
             Node root=this.affiche
;   
              job.printPage(root);
              
              job.endJob(); 
    }
    }
    
}
