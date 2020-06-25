/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reworkfx;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author TE332168
 */
public class UserData {
    
    private final SimpleStringProperty userid, username, useremail, userpsw;
    
    public UserData (String userID, String userName, String userEmail, String userPsw){
        
         //USERS adattábla
        this.userid = new SimpleStringProperty(userID);
        this.username = new SimpleStringProperty(userName);
        this.useremail = new SimpleStringProperty(userEmail);
        this.userpsw = new SimpleStringProperty(userPsw);
        
        
        
    }

    public String getUserid() {
        return userid.get();
    }

    public String getUsername() {
        return username.get();
    }

   
    public String getUseremail() {
        return useremail.get();
    }
   
    public String getUserpsw() {
        return userpsw.get();
    } 
      
    
     public void setUserid(String userID) {
        userid.set(userID);
    }

    public void setUsername(String userName) {
        username.set(userName);
    }

    
    public void setUseremail(String userEmail) {
        useremail.set(userEmail);
    }
    
    public void setUserpsw(String userPsw) {
        userpsw.set(userPsw);
    }
}
