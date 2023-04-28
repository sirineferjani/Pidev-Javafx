/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import java.util.Date;

/**
 *
 * @author lenovo
 */
public class paiement {
    private String firstName , lastName ;
    private int id , phoneNumber , CIN , CreditCard;
    private Date ExpirationDate ;

    public paiement() {
    }

    public paiement(String firstName, String lastName, int id, int phoneNumber, int CIN, int CreditCard, Date ExpirationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.CIN = CIN;
        this.CreditCard = CreditCard;
        this.ExpirationDate = ExpirationDate;
    }

    public paiement(String firstName, String lastName, int phoneNumber, int CIN, int CreditCard, Date ExpirationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.CIN = CIN;
        this.CreditCard = CreditCard;
        this.ExpirationDate = ExpirationDate;
    }

    @Override
    public String toString() {
        return "paiement{" + "firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", phoneNumber=" + phoneNumber + ", CIN=" + CIN + ", CreditCard=" + CreditCard + ", ExpirationDate=" + ExpirationDate + '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public int getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(int CreditCard) {
        this.CreditCard = CreditCard;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }
    
    
    
}
