/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entitie.article;
import entitie.categorie;
import service.articleService;
import service.categorieService;
import utils.DataSource;

/**
 *
 * @author user
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      //  DataSource mc = new DataSource();
      articleService as= new articleService();
       // System.out.println(as.afficherArticle());
      //   article a = new article(70,"camping","aaa",50,"aaa",50);
      // as.ajouterArticle(a);
      articleService cs = new articleService();
    //  categorie c = new categorie("Natation","aaaaa");
    
      //cs.ajouterCategorie(c);
      //cs.supprimerCategorie(53);
    }
    
}
