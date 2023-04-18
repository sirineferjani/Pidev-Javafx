/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author user
 */
public class SMS {
     public static final String ACCOUNT_SID = "ACdf205108665fa554493707ff38b480d9";
    public static final String AUTH_TOKEN = "54f3e2ea28c937028dd5607f108ae1b4";
    public void sms(String nom_article){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try{
            Message message = Message.creator(
                new PhoneNumber("+216 55 007 082"),
                new PhoneNumber("+12766183398"),
                "La quantite du produit"+nom_article+" va expiree bientot.")
            .create();

        System.out.println(message.getSid());
        }catch(Exception e){
            System.out.println(e.getMessage()); 
        }
    }
}
