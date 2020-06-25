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
public class MasterData {
    
    private final SimpleStringProperty masterkeyid, mastername, masteremail, masterphone;
 
    public MasterData(String masterName, String masterEmail, String masterPhone){
        
        this.masterkeyid = new SimpleStringProperty("");
        this.mastername = new SimpleStringProperty(masterName);
        this.masteremail = new SimpleStringProperty(masterEmail);
        this.masterphone = new SimpleStringProperty(masterPhone);
        
    }

    public String getMasterkeyid() {
        return masterkeyid.get();
    }

    public String getMastername() {
        return mastername.get();
    }

    public String getMasteremail() {
        return masteremail.get();
    }

    public String getMasterphone() {
        return masterphone.get();
    }

    public void setMastername(String masterName) {
         mastername.set(masterName);
    }

    public void setMasteremail(String masterEmail) {
         masteremail.set(masterEmail);
    }

    public void setMasterphone(String masterPhone) {
         masterphone.set(masterPhone);
    }
    
}
