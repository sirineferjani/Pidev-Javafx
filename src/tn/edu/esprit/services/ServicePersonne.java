/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import tn.edu.esprit.entities.user;
import tn.edu.esprit.utils.DataSource;

/**
 *
 ** @author nourb
 */
public class ServicePersonne implements IService<user>{
    
    private DataSource ds = DataSource.getInstance();
 


    @Override
    /*public void ajouter(user p) throws SQLException{
        String req = "INSERT INTO `personne` (`nom`, `adresse`, `password` , `role` , `email`) VALUES ('"+p.getNom()+"', '"+p.getPrenom()+"','"+p.getPassword()+"','User','"+p.getEmail()+"')"; 
        //String req = "INSERT INTO `personne` (`nom`, `adresse`, `password` , `role` , `email`,  `image`) VALUES ('"+p.getNom()+"', '"+p.getPrenom()+"','"+p.getPassword()+"','User','"+p.getEmail()+"','"+p.getImage()+"')"; 
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }*/
    public void ajouter(user p) throws SQLException {
    String req = "INSERT INTO personne (nom, adresse, password, role, email, image ,isBanned) VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement ps = ds.getCnx().prepareStatement(req);
    ps.setString(1, p.getNom());
    ps.setString(2, p.getPrenom());
    ps.setString(3, p.getPassword());
    ps.setString(4, "User");
    ps.setString(5, p.getEmail());
    ps.setString(6, p.getImage());
    ps.setString(7, "0");
    ps.executeUpdate();
}

    
  
    
    public void ajouter2(user p) throws SQLException{
        String req = "INSERT INTO `personne` (`nom`, `prenom`, ``) VALUES (?, ?, ?)";;
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, p.getNom());
        st.setString(2, p.getPrenom());
        
        st.executeUpdate();
    }
    
    @Override
    public void modifier(user p) throws SQLException{
    String req = "UPDATE personne SET nom=?, email=?, adresse=?, password=? WHERE id=?";
    //String req = "UPDATE personne SET nom=?, email=?, adresse=? WHERE id=?";
    PreparedStatement ps = ds.getCnx().prepareStatement(req);
    ps.setString(1, p.getNom());
    ps.setString(2, p.getEmail());
    ps.setString(3, p.getPrenom());
    ps.setString(4, p.getPassword());
    ps.setInt(5, p.getId());
    ps.executeUpdate();
}



    @Override
    public void supprimer(int id) throws SQLException{
        String req = "DELETE FROM `personne` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<user> getAll() throws SQLException{
        List<user> list = new ArrayList<>();
        
        String req = "Select * from personne";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            user p = new user( rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) , rs.getString(6));
            list.add(p);
        }
        
        return list;
    }
    
      @Override
        public boolean login(String email, String password) throws SQLException {
         boolean success = false;
    try {
        String req = "SELECT * FROM personne WHERE EMAIL = ?";
        PreparedStatement st = ds.getCnx().prepareStatement(req); 
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        if (rs.next()) { //if (rs.next()) permet donc de vérifier si la requête a renvoyé au moins une ligne de résultat, ce qui signifie que l'utilisateur existe dans la base de données.
            String hashedPassword = hashPassword(password);
            String storedPassword = rs.getString("PASSWORD");
            if (storedPassword.equals(hashedPassword)) {
                success = true;
             
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return success;
    }
      public static String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
        throw new RuntimeException(ex);
    }
}
  /*  public user getUserByEmail(String email) throws SQLException {
    user user =null; //La ligne User user = null; sert simplement à initialiser la variable user à null.
     //Cette variable est ensuite utilisée pour stocker les informations de l'utilisateur récupéré depuis la base de données.

     String req = "SELECT * FROM personne WHERE EMAIL = ?";
     PreparedStatement st = ds.getCnx().prepareStatement(req); 
    
        
        st.setString(1, email); // pour renvoyer le parametre email qui substitue le point d'interrogation bich wa9tha texecuta il requete bil parametre edheka
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                user = new user(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"));
            }
        }
    
    return user;
}*/
    
    
  public user getUserByEmail2(String email) throws SQLException {
    user user =null; //La ligne User user = null; sert simplement à initialiser la variable user à null.
     //Cette variable est ensuite utilisée pour stocker les informations de l'utilisateur récupéré depuis la base de données.

     String req = "SELECT * FROM personne WHERE EMAIL = ?";
     PreparedStatement st = ds.getCnx().prepareStatement(req); 
    
        
        st.setString(1, email); // pour renvoyer le parametre email qui substitue le point d'interrogation bich wa9tha texecuta il requete bil parametre edheka
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                user = new user(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("image"));
                        
            }
        }
    
    return user;
}
   

       public void updateUser(user user) throws SQLException {
        String req = "UPDATE users SET nom = ?, email = ?, prenom = ? WHERE id = ?";
       PreparedStatement ps = ds.getCnx().prepareStatement(req);
        ps.setString(1, user.getNom());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPrenom());
        ps.setInt(4, user.getId());
        ps.executeUpdate();
    }
       
       
         public ObservableList<user> afficherusers() {
       ObservableList<user> myList= FXCollections.observableArrayList();
        
    
        try {
            String sql = "SELECT * FROM personne WHERE role = 'User'";
            Statement ste=ds.getCnx().createStatement();
            ResultSet rs= ste.executeQuery(sql);
            while(rs.next()){
                 user p = new user();
                 p.setId(rs.getInt(1));
                 p.setNom(rs.getString("nom")); 
                 p.setEmail(rs.getString("email"));
                 p.setPrenom(rs.getString("adresse"));
                 p.setImage(rs.getString("image"));
         
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
         
     public void supprimeruser(user us) {
        try {
            String req = "DELETE FROM `personne` WHERE id = ?";
            PreparedStatement ste=ds.getCnx().prepareStatement(req);
            ste.setInt(1, us.getId());
            ste.executeUpdate();
            System.out.println("user supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     //ban avant l'ajout de la durée
public void Ban(user u) {
    try {
        String req = "UPDATE personne SET isBanned = CASE WHEN isBanned = 0 THEN 1 ELSE 0 END WHERE id=?";
        PreparedStatement ste = ds.getCnx().prepareStatement(req);
        ste.setInt(1, u.getId());
        int rowsUpdated = ste.executeUpdate();
        if(rowsUpdated > 0) {
            System.out.println("User_id " + u.getNom() + ":" + " Bloqué !");
        } else {
            System.out.println("Aucune ligne n'a été mise à jour. Vérifiez la valeur de u.getId().");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

public void banSelonDuree(user u, int duration) {
    try {
        LocalDateTime unblockTime = LocalDateTime.now().plusMinutes(duration);
        String req = "UPDATE personne SET isBanned = 1, unblockTime = ? WHERE id=?";
        PreparedStatement ste = ds.getCnx().prepareStatement(req);
        ste.setTimestamp(1, Timestamp.valueOf(unblockTime));
        ste.setInt(2, u.getId());
        int rowsUpdated = ste.executeUpdate();
        if(rowsUpdated > 0) {
            System.out.println("User_id " + u.getNom() + ":" + " Bloqué pour " + duration + " minutes !");
        } else {
            System.out.println("Aucune ligne n'a été mise à jour. Vérifiez la valeur de u.getId().");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

public boolean isBanned(user u) {
    try {
        String req = "SELECT isBanned, unblockTime FROM personne WHERE id=?";
        PreparedStatement ste = ds.getCnx().prepareStatement(req);
        ste.setInt(1, u.getId());
        ResultSet rs = ste.executeQuery();
        if (rs.next()) {
            boolean isBanned = rs.getBoolean("isBanned");
            if (isBanned) {
                LocalDateTime unblockTime = rs.getTimestamp("unblockTime").toLocalDateTime();
                if (unblockTime.isBefore(LocalDateTime.now())) {
                    // Si la date de déblocage est passée, débloquer l'utilisateur
                    banSelonDuree(u, 0);
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            System.out.println("Aucun utilisateur avec cet ID n'a été trouvé.");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}


    
   
}