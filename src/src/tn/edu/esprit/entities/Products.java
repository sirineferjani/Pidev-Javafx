/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

public class Products  {

  
   private final StringProperty id;
    private final StringProperty name;
    private final StringProperty reference;
    private final StringProperty  description;
        private final StringProperty  statut;


    public Products()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        reference = new SimpleStringProperty(this, "reference");
        description = new SimpleStringProperty(this, "description");
         statut = new SimpleStringProperty(this, "statut");


    }


    

   

   
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }
 
    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }
 
    public StringProperty referenceProperty() { return reference; }
    public String getReference() { return reference.get(); }
    public void setReference(String newReference) { reference.set(newReference); }
    
    public StringProperty descriptionProperty() { return description; }
    public String getDescription() { return description.get(); }
    public void setDescription(String newDescription) { description.set(newDescription); }
    
    
    
     public StringProperty statutProperty() { return statut; }
    public String getStatut() { return statut.get(); }
    public void setStatut(String newStatut) { statut.set(newStatut); }

    public void setImageSrc(String imageteremovebgpreviewpng) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getImageSrc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
