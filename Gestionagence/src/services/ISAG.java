/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Amirov
 */
public interface ISAG<T> {
    public void ajouterAg(T c);
     public List<T> afficherAg();
     public void modiferAg(T c);
    public void supprimerAg(int id);
}
