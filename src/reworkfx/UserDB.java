/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reworkfx;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author TE332168
 */
public class UserDB {
   
    final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    final String URL = "jdbc:derby:reworkDB; create = true";
    final String BENUTZERNAME = "";
    final String KENNWORT = "";
    
    Dialogs msg = new Dialogs();
       
    Connection cntn = null;
    Statement createStmt = null;
    DatabaseMetaData dbmetadata = null;
    PreparedStatement preparedStmt = null;
    
    public void addNewAppUser(UserData rwusers){
         
        String add_query = "insert into users (userdb_id, userdb_name, userdb_email, userdb_psw) values (?,?,?,?)";
                
        try{
         preparedStmt = cntn.prepareStatement(add_query);   
            preparedStmt.setString(1, rwusers.getUserid());
            preparedStmt.setString(2, rwusers.getUsername());
            preparedStmt.setString(3, rwusers.getUseremail());
            preparedStmt.setString(4, rwusers.getUserpsw());
            preparedStmt.execute();
       } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "PrepareStatement", "UserData adatbázishiba:\n"+e);
    }
}
    
    public ArrayList<UserData> getUsers(){ 
        String query = "SELECT * FROM users";       
        ArrayList<UserData> rwusers = null;
            
            try{
                
                ResultSet rs = createStmt.executeQuery(query);
                rwusers = new ArrayList<>();
                while (rs.next()){
                    UserData selectedUser = new UserData(rs.getString("userdb_id"), rs.getString("userdb_name"), rs.getString("userdb_email"), rs.getString("userdb_psw"));
                        rwusers.add(selectedUser);
                }
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getUsers", "Lekérdezéshiba:\n"+e);
        }
        return rwusers;
}
    
    public void deleteUsersTable(){
        
        try{
        createStmt.execute("drop table users"); //Developer mode - db törlése
        createStmt.execute("create table users"); //Developer mode - üres db létrehozása
        }
        catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "DeleteUsers", "Felhasználók törlése nem hajtható végre!:\n"+e);
        }
    }
    
    
    public Boolean checkUserId(String data){ 
        String query = "SELECT * FROM users";       
        String selectedUser = "none";
        Boolean userexists = false;
            try{
                
                ResultSet rs = createStmt.executeQuery(query);
                        
                while (rs.next()){
                    selectedUser = rs.getString("userdb_id");
                    if (selectedUser.equals(data)){
                        msg.showAlertBox(Alert.AlertType.CONFIRMATION,"Figyelem!", "Már van ilyen azonosító: "+selectedUser);
                        userexists = true;
                    }    
                }
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getUsers", "Lekérdezéshiba:\n"+e);
        }
        return userexists;
}
        
    
    public UserDB(){
           
      try{
            cntn = DriverManager.getConnection(URL);
            msg.showAlertBox(Alert.AlertType.INFORMATION, "GetConnection UserDB", "Adatbáziskapcsolat létrejött:\n"+URL);
        }catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "GetConnection", "Adatbáziskapcsolat nem jött létre:\n"+e);
        }
        
        if (cntn != null){
            try{
                createStmt = cntn.createStatement();
               }catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "CreateStatement", "Adatbáziskapcsolat nem jött létre:\n"+e);
        }
        }
        
        try{
            dbmetadata = cntn.getMetaData();
        }catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "GetMetaData", "Adatbáziskapcsolat nem jött létre:\n"+e);
    }
        
        try{
            
            ResultSet rsrew = dbmetadata.getTables(null,"APP", "USERS", null);
            if (!rsrew.next()){
            createStmt.execute("create table users(userdb_keyid INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), userdb_id varchar(8), userdb_name varchar(10), userdb_email varchar(30), userdb_psw varchar(16))");
                    
            }
            }catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "ResultSet", "Adatbázishiba:\n"+e);
        }
    }
}
