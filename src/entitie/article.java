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
public class article {
    private int id;
    private int ref_article;
    private String nom_article;
    private String description;
    private int prix;
    private String image;
    private int stock;
    private float note;

    public article() {
    }
    

    public article(int id, int ref_article, String nom_article, String description, int prix, String image, int stock, float note) {
        this.id = id;
        this.ref_article = ref_article;
        this.nom_article = nom_article;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.stock = stock;
        this.note = note;
    }

    public article(int ref_article, String nom_article, String description, int prix, String image, int stock, float note) {
        this.ref_article = ref_article;
        this.nom_article = nom_article;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.stock = stock;
        this.note = note;
    }

    public article(int ref_article, String nom_article, String description, int prix, String image, int stock) {
        this.ref_article = ref_article;
        this.nom_article = nom_article;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.stock = stock;
    }


    public int getId() {
        return id;
    }

    public int getRef_article() {
        return ref_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public String getDescription() {
        return description;
    }

    public int getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public int getStock() {
        return stock;
    }

    public float getNote() {
        return note;
    }

    public void setRef_article(int ref_article) {
        this.ref_article = ref_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
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
        final article other = (article) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "article{" + "id=" + id + ", ref_article=" + ref_article + ", nom_article=" + nom_article + ", description=" + description + ", prix=" + prix + ", image=" + image + ", stock=" + stock + ", note=" + note + '}';
    }
    
    
    
}
