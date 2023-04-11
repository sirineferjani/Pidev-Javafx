/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import tn.edu.esprit.entities.user;

/**
 *
 * * @author nourb
 */
public interface IService <T>{
    public void ajouter(T p) throws SQLException;
    //public void ajouter(user utilisateur, File imageFile) throws SQLException;
    public void modifier(T p) throws SQLException;
    public void supprimer(int id) throws SQLException;
    public List<T> getAll() throws SQLException;
    public boolean login(String email, String password) throws SQLException;
   
}
