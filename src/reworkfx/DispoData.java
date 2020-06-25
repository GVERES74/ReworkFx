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
public class DispoData {
    
    private final SimpleStringProperty dispokeyid, disponame, dispoemail, dispophone;
 
    public DispoData(String dispoName, String dispoEmail, String dispoPhone){
        
        this.dispokeyid = new SimpleStringProperty("");
        this.disponame = new SimpleStringProperty(dispoName);
        this.dispoemail = new SimpleStringProperty(dispoEmail);
        this.dispophone = new SimpleStringProperty(dispoPhone);
        
    }

    public String getDispokeyid() {
        return dispokeyid.get();
    }

    public String getDisponame() {
        return disponame.get();
    }

    public String getDispoemail() {
        return dispoemail.get();
    }

    public String getDispophone() {
        return dispophone.get();
    }

    public void setDisponame(String dispoName) {
         disponame.set(dispoName);
    }

    public void setDispoemail(String dispoEmail) {
         dispoemail.set(dispoEmail);
    }

    public void setDispophone(String dispoPhone) {
         dispophone.set(dispoPhone);
    }
    
    
}
