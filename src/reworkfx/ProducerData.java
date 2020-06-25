/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reworkfx;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author VERESG
 */
public class ProducerData {
    
    private final SimpleStringProperty producerkeyid, producername, produceremail, producerphone;
 
    public ProducerData(String producerName, String producerEmail, String producerPhone){
        
        this.producerkeyid = new SimpleStringProperty("");
        this.producername = new SimpleStringProperty(producerName);
        this.produceremail = new SimpleStringProperty(producerEmail);
        this.producerphone = new SimpleStringProperty(producerPhone);
        
    }

    public String getProducerkeyid() {
        return producerkeyid.get();
    }

    public String getProducername() {
        return producername.get();
    }

    public String getProduceremail() {
        return produceremail.get();
    }

    public String getProducerphone() {
        return producerphone.get();
    }

    public void setProducername(String producerName) {
         producername.set(producerName);
    }

    public void setProduceremail(String producerEmail) {
         produceremail.set(producerEmail);
    }

    public void setProducerphone(String producerPhone) {
         producerphone.set(producerPhone);
    }
    
}
