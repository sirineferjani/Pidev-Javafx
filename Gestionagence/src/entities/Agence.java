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
public class Agence {
      private int id,nbr_participant,devis,num_tel,activite_id   ;
    private String nom ;

    public Agence() {
    }

    public Agence(int id, int nbr_participant, int devis, int num_tel, String nom) {
        this.id = id;
        this.nbr_participant = nbr_participant;
        this.devis = devis;
        this.num_tel = num_tel;
       
        this.nom = nom;
    }

    public Agence(int id, int nbr_participant, int devis, int num_tel, int activite_id, String nom) {
        this.id = id;
        this.nbr_participant = nbr_participant;
        this.devis = devis;
        this.num_tel = num_tel;
        this.activite_id = activite_id;
        this.nom = nom;
    }

    public Agence(int nbr_participant, int devis, int num_tel, String nom) {
        this.nbr_participant = nbr_participant;
        this.devis = devis;
        this.num_tel = num_tel;
        this.nom = nom;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr_participant() {
        return nbr_participant;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    public int getDevis() {
        return devis;
    }

    public void setDevis(int devis) {
        this.devis = devis;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public int getActivite_id() {
        return activite_id;
    }

    public void setActivite_id(int activite_id) {
        this.activite_id = activite_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", nbr_participant=" + nbr_participant + ", devis=" + devis + ", num_tel=" + num_tel + ", activite_id=" + activite_id + ", nom=" + nom + '}';
    }
    
    
    
}
