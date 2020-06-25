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
public class ProductionData {
    
    private final SimpleStringProperty prodkeyid, prodareano, prodareaname, costareano, producername;
 
    public ProductionData(String prodareaNo, String prodareaName, String costareaNo, String producerName){
        
        this.prodkeyid = new SimpleStringProperty("");
        this.prodareano = new SimpleStringProperty(prodareaNo);
        this.prodareaname = new SimpleStringProperty(prodareaName);
        this.costareano = new SimpleStringProperty(costareaNo);
        this.producername = new SimpleStringProperty(producerName);
        
    }

    public String getProdkeyid() {
        return prodkeyid.get();
    }

    public String getProdareano() {
        return prodareano.get();
    }

    public String getProdareaname() {
        return prodareaname.get();
    }
      
    public String getCostareano() {
        return costareano.get();
    }

    public String getProducername() {
        return producername.get();
    }
    
    
    
    public void setProdareano(String prodareaNo){
        prodareano.set(prodareaNo);
    }
    
    public void setProdareaname(String prodareaName){
        prodareaname.set(prodareaName);
    }
    
    public void setCostareano(String costareaNo){
        costareano.set(costareaNo);
    }
    
    public void setProducername(String producerName){
        producername.set(producerName);
    }
}
