/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id")
    , @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name")
    , @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price")
    , @NamedQuery(name = "Products.findByReference", query = "SELECT p FROM Products p WHERE p.reference = :reference")
    , @NamedQuery(name = "Products.findByStaut", query = "SELECT p FROM Products p WHERE p.staut = :staut")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "reference")
    private int reference;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "statut")
    private String statut;

    public Products() {
        super();
    }

    public Products(Integer id) {
        this.id = id;
    }

    public Products(Integer id, String name, double price, int reference, String description, String statut) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reference = reference;
        this.description = description;
        this.statut = statut;
    }

    public Products(String name, double price, int reference, String description, String statut) {
        this.name = name;
        this.price = price;
        this.reference = reference;
        this.description = description;
        this.statut = statut;
    }

    

    public Products(String name, String description, int reference, String statut, double price) {
        this.name = name;
        this.price = price;
        this.reference = reference;
        this.description = description;
        this.statut = statut;
        this.price=price;
    }

    public Products(int id, String name, String description, String statut, double price) {
        this.id=id;
         this.name = name;
        this.description = description;
        this.statut = statut;
        this.price=price;
    }

   

   

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.edu.esprit.entities.Products[ id=" + id + " ]";
    }

  
}
