/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Agence;
import java.sql.Connection;
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
public class ServiceAg implements ISAG<Agence> {
private Connection cnx ;
 
public ServiceAg() {
    cnx = MyDB.getInstance().getcnx();
}

    @Override
    public void ajouterAg(Agence c) {
        try {
             String querry="INSERT INTO `agence`(`nom`, `nbr_participant`, `devis`, `num_tel` ) VALUES ('"+c.getNom()+"','"+c.getNbr_participant()+"','"+c.getDevis()+"','"+c.getNum_tel()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        System.out.println("Cours ajouter avec sucée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    }

    @Override
    public List<Agence> afficherAg() {
        List<Agence> agence = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `agence`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Agence a = new Agence();
            a.setId(rs.getInt(1));
            a.setNom(rs.getString(2));
            a.setNbr_participant(rs.getInt(3));
            a.setDevis(rs.getInt(4));
            a.setNum_tel(rs.getInt(5));
            a.setActivite_id(rs.getInt(6));

            
            
            agence.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
             }
   
        return agence;

    }

    @Override
    public void modiferAg(Agence c) {
        try {
                   String querry = "UPDATE `agence` SET `id`='" + c.getId()+"', `nom`='" + c.getNom()+ "' , `nbr_participant`='" + c.getNbr_participant()+ "', `devis`='" + c.getDevis()+ "', `num_tel`='" + c.getNum_tel()+ "' WHERE id=" + c.getId();
                   Statement stm =cnx.createStatement();
                   stm.executeUpdate(querry);
                   System.out.println("L agence est modifée !");
               } catch (SQLException ex) {
                   System.out.println(ex.getMessage());
               }
    }

    @Override
    public void supprimerAg(int id) {
         try {
            String querry = "DELETE FROM `agence` WHERE id="+id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("Agence supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
}
