/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Amirov
 */
public class Activite {
     private int id, age ;
    private String nom,	description,lieu,image ;

    public Activite() {
    }

    public Activite(int age, String nom, String description, String lieu, String image) {
        this.age = age;
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
        this.image = image;
    }

    public Activite(int id, int age, String nom, String description, String lieu, String image) {
        this.id = id;
        this.age = age;
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", age=" + age + ", nom=" + nom + ", description=" + description + ", lieu=" + lieu + ", image=" + image + '}';
    }
    
    
    
}
