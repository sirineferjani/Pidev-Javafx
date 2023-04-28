/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionagence;

import entities.Activite;
import entities.Agence;
import services.ServiceAc;
import services.ServiceAg;

/**
 *
 * @author Amirov
 */
public class Gestionagence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ServiceAc sa = new ServiceAc() ;
        ServiceAg sg = new ServiceAg() ;
        
       // Activite c = new Activite(22,"foot","belle activité","Kef","dsq.jpg");
      //sa.ajouterAc(c);
        
   //   sa.supprimerAc(7);
      
  // Agence c = new Agence(32,12,12,"taha voy");
    //  sg.ajouterAg(c);
      
       // System.out.println( sa.afficherAc().toString());
        System.out.println( sg.afficherAg().toString());
    
    }
    
}
