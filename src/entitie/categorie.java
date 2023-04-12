/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

/**
 *
 * @author user
 */
public class categorie {
    private int id;
    private String nom_c;
    private String image_c;

    public categorie() {
    }

    public categorie(int id, String nom_c, String image_c) {
        this.id = id;
        this.nom_c = nom_c;
        this.image_c = image_c;
    }

    public categorie(String nom_c, String image_c) {
        this.nom_c = nom_c;
        this.image_c = image_c;
        
    }

    public int getId() {
        return id;
    }

    public String getNom_c() {
        return nom_c;
    }

    public String getImage_c() {
        return image_c;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public void setImage_c(String image_c) {
        this.image_c = image_c;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final categorie other = (categorie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", nom_c=" + nom_c + ", image_c=" + image_c + '}';
    }
    
}
