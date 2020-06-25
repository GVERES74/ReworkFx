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
public class ReworkData {
 
    private final SimpleStringProperty autoid, reworkazon, claimazon, reworklocazon, costperhour, partno, partname, chargeno, qtyforrework, secperpiece, reworkhours, calculatedcostshuf, calculatedcostseur;
    private final SimpleStringProperty defectdescription, defectphoto, issuedatum, deadlinedatum, usernev, prodareano, prodareaname, costareano, producername, produceremail, producerphone, disponame, dispoemail, dispophone;

    
    public ReworkData(String reworkID, String claimID, String reworkLocID, String costPerHour, String partID, String partName, String chargeNo, String qtY, String pieceSec, 
            String reworkHrs, String costHuf, String costEur, String defectDesc, String defectPhoto, String issueDate, String deadlineDate, String userName, 
            String prodareaNo, String prodareaName, String costareaNo, String producerName, String producerEmail, String producerPhone, String dispoName, String dispoEmail, String dispoPhone){
    
        //REWORKS adattábla
        this.autoid = new SimpleStringProperty("");
        this.reworkazon = new SimpleStringProperty(reworkID);
        this.claimazon = new SimpleStringProperty(claimID);
        this.reworklocazon = new SimpleStringProperty(reworkLocID);
        this.costperhour = new SimpleStringProperty(costPerHour);
        
        //MATERIALS adattábla
        this.partno = new SimpleStringProperty(partID);
        this.partname = new SimpleStringProperty(partName);
        
        //REWORKS adattábla
        this.chargeno = new SimpleStringProperty(chargeNo);
        this.qtyforrework = new SimpleStringProperty(qtY);
        this.secperpiece = new SimpleStringProperty(pieceSec);
        this.reworkhours = new SimpleStringProperty(reworkHrs);
        this.calculatedcostshuf = new SimpleStringProperty(costHuf);
        this.calculatedcostseur = new SimpleStringProperty(costEur);
        this.defectdescription = new SimpleStringProperty(defectDesc);
        this.defectphoto = new SimpleStringProperty(defectPhoto);
        this.issuedatum = new SimpleStringProperty(issueDate);
        this.deadlinedatum = new SimpleStringProperty(deadlineDate);
        this.usernev = new SimpleStringProperty(userName);
        
        //PRODUCTION adattábla
        this.prodareano = new SimpleStringProperty(prodareaNo);
        this.prodareaname = new SimpleStringProperty(prodareaName);
        this.costareano = new SimpleStringProperty(costareaNo);
        
        //PRODUCERS adattábla
        this.producername = new SimpleStringProperty(producerName);
        this.produceremail = new SimpleStringProperty(producerEmail);
        this.producerphone = new SimpleStringProperty(producerPhone);
        
        //DISPOSITION adattábla
        this.disponame = new SimpleStringProperty(dispoName);
        this.dispoemail = new SimpleStringProperty(dispoEmail);
        this.dispophone = new SimpleStringProperty(dispoPhone);
    }

       
       
     public ReworkData(){
            
        this.autoid = new SimpleStringProperty("");
        this.reworkazon = new SimpleStringProperty("");
        this.claimazon = new SimpleStringProperty("");
        this.reworklocazon = new SimpleStringProperty("");
        this.costperhour = new SimpleStringProperty("");
        
        this.partno = new SimpleStringProperty("");
        this.partname = new SimpleStringProperty("");
        
        this.chargeno = new SimpleStringProperty("");
        this.qtyforrework = new SimpleStringProperty("");
        this.secperpiece = new SimpleStringProperty("");
        this.reworkhours = new SimpleStringProperty("");
        this.calculatedcostshuf = new SimpleStringProperty("");
        this.calculatedcostseur = new SimpleStringProperty("");
        this.defectdescription = new SimpleStringProperty("");
        this.defectphoto = new SimpleStringProperty("");
        this.issuedatum = new SimpleStringProperty("");
        this.deadlinedatum = new SimpleStringProperty("");
        this.usernev = new SimpleStringProperty("");
        
        
        this.prodareano = new SimpleStringProperty("");
        this.prodareaname = new SimpleStringProperty("");
        this.costareano = new SimpleStringProperty("");
        
        this.producername = new SimpleStringProperty("");
        this.produceremail = new SimpleStringProperty("");
        this.producerphone = new SimpleStringProperty("");
        
        this.disponame = new SimpleStringProperty("");
        this.dispoemail = new SimpleStringProperty("");
        this.dispophone = new SimpleStringProperty("");
    }

    public String getAutoid() {
        return autoid.get();
    }
        
    public String getReworkazon() {
        return reworkazon.get();
    }

    public String getClaimazon() {
        return claimazon.get();
    }

    public String getReworklocazon() {
        return reworklocazon.get();
    }

    public String getCostperhour() {
        return costperhour.get();
    }

    
    public String getPartno() {
        return partno.get();
    }

    public String getPartname() {
        return partname.get();
    }

    public String getChargeno() {
        return chargeno.get();
    }

    public String getQtyforrework() {
        return qtyforrework.get();
    }

    public String getSecperpiece() {
        return secperpiece.get();
    }

    public String getReworkhours() {
        return reworkhours.get();
    }

    public String getCalculatedcostshuf() {
        return calculatedcostshuf.get();
    }

    public String getCalculatedcostseur() {
        return calculatedcostseur.get();
    }

    public String getDefectdescription() {
        return defectdescription.get();
    }

    public String getDefectphoto() {
        return defectphoto.get();
    }
    
    public String getIssuedatum() {
        return issuedatum.get();
    }

    public String getDeadlinedatum() {
        return deadlinedatum.get();
    }

    public String getUsernev() {
        return usernev.get();
    }

    public String getProdareaazon() {
        return prodareano.get();
    }

    public String getProdareanev() {
        return prodareaname.get();
    }
    
    public String getCostareaazon() {
        return costareano.get();
    }

    public String getProducernev() {
        return producername.get();
    }

    public String getProduceremail() {
        return produceremail.get();
    }

    public String getProducertelefon() {
        return producerphone.get();
    }

    public String getDisponev() {
        return disponame.get();
    }

    public String getDispoemail() {
        return dispoemail.get();
    }

    public String getDispotelefon() {
        return dispophone.get();
    }

    public void setReworkazon(String reworkID) {
        reworkazon.set(reworkID);
    }

    public void setClaimazon(String claimID) {
        claimazon.set(claimID);
    }

    public void setPartazon(String partID) {
        partno.set(partID);
    }

    public void setPartnev(String partName) {
        partname.set(partName);
    }

    public void setChargeazon(String chargeNo) {
        chargeno.set(chargeNo);
    }

    public void setIssuedatum(String issueDate) {
        issuedatum.set(issueDate);
    }

    public void setDeadlinedatum(String deadlineDate) {
        deadlinedatum.set(deadlineDate);
    }

    public void setUsernev(String userName) {
        usernev.set(userName);
    }

    public void setProdareaazon(String prodareaNo) {
        prodareano.set(prodareaNo);
    }
    
    public void setProdareanev(String prodareaName) {
        prodareaname.set(prodareaName);
    }

    public void setCostareaazon(String costareaNo) {
        costareano.set(costareaNo);
    }

    public void setMasternev(String producerName) {
        producername.set(producerName);
    }

    public void setMasteremail(String producerEmail) {
        produceremail.set(producerEmail);
    }

    public void setMastertelefon(String producerPhone) {
        producerphone.set(producerPhone);
    }
        
    public void setDisponev(String dispoName) {
        disponame.set(dispoName);
    }

    public void setDispoemail(String dispoEmail) {
        dispoemail.set(dispoEmail);
    }

    public void setDispotelefon(String dispoPhone) {
        dispophone.set(dispoPhone);
    }
}
