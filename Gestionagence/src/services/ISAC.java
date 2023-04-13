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
public interface ISAC<T> {
    public void ajouterAc(T c);
     public List<T> afficherAc();
     public void modiferAc(T c);
    public void supprimerAc(int id);
}
