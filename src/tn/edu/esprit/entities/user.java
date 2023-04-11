/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author nourb
 */
public class user {
    private int id;
    private String nom, prenom ,password ,role ,email;
    byte[] image;

    public user(String nom, String prenom, String password, String email, byte[] image) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.image = image;
    }

    public user(String nom, String prenom, String password, String role, String email, byte[] image) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.role = role;
        this.email = email;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
   
    
    public String getRole() {
        return role;
    }

    public user(String nom, String prenom, String password, String role, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public user(String nom, String prenom, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public user() {
    }

    public user(int id, String nom, String prenom, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.role = role;
    }

    public user(String nom, String prenom, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.role = role;
    }

    
 

   
}
