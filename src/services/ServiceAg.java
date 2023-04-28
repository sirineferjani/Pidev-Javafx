/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Agence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
             String querry="INSERT INTO `agence`(`nom`, `nbr_participant`, `devis`, `num_tel`,`activite_id` ) VALUES ('"+c.getNom()+"','"+c.getNbr_participant()+"','"+c.getDevis()+"','"+c.getNum_tel()+"','"+c.getActivite_id()+"')";
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

    public Agence ReadByNVid(int id) {
        Agence ee = new Agence();
        String condition = (" activite_id ='" + id + "'");
        String requete = "SELECT * FROM agence WHERE " + condition + " LIMIT 1";
        try {
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                ee = new Agence(
                        rs.getInt("id"),
                        rs.getInt("nbr_participant"),
                        rs.getInt("devis"),
                        rs.getInt("num_tel"),
                        rs.getInt("activite_id"),
                        rs.getString("nom"));

            }
        } catch (SQLException e) {
            System.out.println("Error ReadByID Exception = \n" + e);
        }
        return ee;
    }

    @Override
    public void ReadById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public List<Agence> Search(String t) {

        List<Agence> list1 = new ArrayList<>();
        List<Agence> list2 = afficherAg();
        list1 = (list2.stream().filter(c -> c.getNom().startsWith(t)).collect(Collectors.toList()));
        
    return list1;
    }

    public List<Agence> triNom() {

        List<Agence> list1 = new ArrayList<>();
        List<Agence> list2 = afficherAg();

        list1 = list2.stream().sorted((o1, o2) -> o1.getNom().compareTo(o2.getNom())).collect(Collectors.toList());
        return list1;

    }
    public List<Agence> triQuantity() {

        List<Agence> list1 = new ArrayList<>();
        List<Agence> list2 = afficherAg();

        list1 = list2.stream().sorted((q1, q2) -> Integer.compare(q1.getNum_tel(), q2.getNum_tel()))
             .collect(Collectors.toList());
        return list1;

    }
    


}
