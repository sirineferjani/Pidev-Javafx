/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Activite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Amirov
 */
public class ServiceAc implements ISAC<Activite> {
private Connection cnx ;
 
public ServiceAc() {
    cnx = MyDB.getInstance().getcnx();
}

    @Override
    public void ajouterAc(Activite c) {
        try {
             String querry="INSERT INTO `activite`(`nom`, `description`, `age`, `lieu`, `image` ) VALUES ('"+c.getNom()+"','"+c.getDescription()+"','"+c.getAge()+"','"+c.getLieu()+"','"+c.getImage()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        System.out.println("Activité ajouter avec sucée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    }
    @Override
    public List<Activite> afficherAc() {
        List<Activite> activite = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `activite`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Activite a = new Activite();
            a.setId(rs.getInt(1));
            a.setNom(rs.getString(2));
            a.setDescription(rs.getString(3));
            a.setAge(rs.getInt(4));
            a.setLieu(rs.getString(5));
            a.setImage(rs.getString(6));

            
            
            activite.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
        return activite;

    }

    @Override
    public void modiferAc(Activite c) {
          try {
            String querry = "UPDATE `activite` SET `id`='" + c.getId()+"', `nom`='" + c.getNom()+ "' , `description`='" + c.getDescription()+ "', `age`='" + c.getAge()+ "',`lieu`='" + c.getLieu()+ "', `image`='" + c.getImage()+ "' WHERE id=" + c.getId();
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("L Activité est modifée avec succée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerAc(int id) {
         try {
            String querry = "DELETE FROM `activite` WHERE id="+id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("Activité supprimée avec succée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
     public List<Activite> Rechercher(String critere,String rec) {
      
        List<Activite> Activites = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM activite where "+critere+" like '"+rec+"%'";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Activite A = new Activite();
            A.setId(rs.getInt(1));
           A.setNom(rs.getString(2));
      A.setAge(rs.getInt(3));
      A.setDescription(rs.getString(4));
      A.setLieu(rs.getString(5));
      A.setImage(rs.getString(6));

            
            
            
            Activites.add(A);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return Activites;
   
    }
    
}
