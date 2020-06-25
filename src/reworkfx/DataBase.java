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
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.Alert;

/**
 *
 * @author VERESG
 */
public class DataBase {
    
    final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    final String URL = "jdbc:derby:reworkDB; create = true";
    final String BENUTZERNAME = "";
    final String KENNWORT = "";
    
    Dialogs msg = new Dialogs();
       
    Connection cntn = null;
    Statement createStmt = null;
    DatabaseMetaData dbmetadata = null;
    PreparedStatement preparedStmt = null;
    
        
    public ArrayList<ReworkData> getAllRecords(String fquery){ //Konstruktor 1 az összes rekordra
               
        ArrayList<ReworkData> rwrecords = null;
            
            try{
                
                ResultSet rs = createStmt.executeQuery(fquery);
                rwrecords = new ArrayList<>();
                while (rs.next()){
                    ReworkData actualRecord = new ReworkData(rs.getString("db_rpo"), rs.getString("db_claimno"), rs.getString("db_reworkloctext"),rs.getString("db_costperhour"),rs.getString("db_partno"), rs.getString("db_partname"), 
                       rs.getString("db_batch"), rs.getString("db_qty"),rs.getString("db_secperpcs"),rs.getString("db_hourperqty"),rs.getString("db_calccosthuf"),rs.getString("db_calccosteur"),
                       rs.getString("db_defect"), rs.getString("db_defectpic"), rs.getString("db_picktoday"), rs.getString("db_pickdeadline"), rs.getString("db_current_user"), rs.getString("db_prodareaid"), rs.getString("db_prodareaname"), rs.getString("db_costarea"),
                       rs.getString("db_producername"), rs.getString("db_produceremail"), rs.getString("db_producerphone"), rs.getString("db_disponame"), rs.getString("db_dispoemail"), rs.getString("db_dispophone"));
                            
                    rwrecords.add(actualRecord);
                }
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getAllRecords Konstruktor 1", "Lekérdezéshiba:\n"+e);
        }
        return rwrecords;
}
    
    public ArrayList<ReworkData> getAllRecords(String combodata, String combovalue){ //Konstruktor 2 a szűrni kívánt rekordokra
        String filter_query = "SELECT * FROM reworks WHERE combovalue = combodata";       
        ArrayList<ReworkData> rwrecords = null;
            
            try{
                preparedStmt = cntn.prepareStatement(filter_query);   
                preparedStmt.setString(1, combodata);
                preparedStmt.setString(2, combovalue);
                preparedStmt.execute();
                ResultSet rs = createStmt.executeQuery(filter_query);
                rwrecords = new ArrayList<>();
                while (rs.next()){
                    ReworkData actualRecord = new ReworkData(rs.getString("db_rpo"), rs.getString("db_claimno"), rs.getString("db_reworkloctext"),rs.getString("db_costperhour"),rs.getString("db_partno"), rs.getString("db_partname"), 
                       rs.getString("db_batch"), rs.getString("db_qty"),rs.getString("db_secperpcs"),rs.getString("db_hourperqty"),rs.getString("db_calccosthuf"),rs.getString("db_calccosteur"),
                       rs.getString("db_defect"), rs.getString("db_defectpic"), rs.getString("db_picktoday"), rs.getString("db_pickdeadline"), rs.getString("db_current_user"), rs.getString("db_prodareaid"), rs.getString("db_prodareaname"), rs.getString("db_costarea"),
                       rs.getString("db_producername"), rs.getString("db_produceremail"), rs.getString("db_producerphone"), rs.getString("db_disponame"), rs.getString("db_dispoemail"), rs.getString("db_dispophone"));
                            
                    rwrecords.add(actualRecord);
                }
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getAllRecords Konstruktor 2", "Lekérdezéshiba:\n"+e);
        }
        return rwrecords;
}
    
    
 
     public ArrayList<MaterialData> getAllMaterials(){ 
        String query = "SELECT * FROM materials";       
        ArrayList<MaterialData> rwmaterials = null;
            
            try{
                
                ResultSet rs = createStmt.executeQuery(query);
                rwmaterials = new ArrayList<>();
                while (rs.next()){
                    MaterialData actualMaterial = new MaterialData(rs.getString("db_mat_nr"), rs.getString("db_mat_desc"), rs.getString("db_production_prodnr"), rs.getString("db_disp_name"));
                        rwmaterials.add(actualMaterial);
                }
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getAllMaterials", "Lekérdezéshiba:\n"+e);
        }
        return rwmaterials;
}
     
     public ArrayList<ProductionData> getAllProdareas(){ 
        String query = "SELECT * FROM production";       
        ArrayList<ProductionData> rwprodareas = null;
            
            try{
                
                ResultSet rs = createStmt.executeQuery(query);
                rwprodareas = new ArrayList<>();
                while (rs.next()){
                    ProductionData actualProdarea = new ProductionData(rs.getString("db_production_prodnr"), rs.getString("db_production_prodname"), rs.getString("db_production_costnr"), rs.getString("db_producer_name"));
                        rwprodareas.add(actualProdarea);
                }
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getAllProdareas", "Lekérdezéshiba:\n"+e);
        }
        return rwprodareas;
}
    
     public ArrayList<ProducerData> getAllProducers(){ 
        String query = "SELECT * FROM producers";  
        ArrayList<ProducerData> rwproducers = null;
            
            try{
                
                ResultSet rs = createStmt.executeQuery(query);
                rwproducers = new ArrayList<>();
                while (rs.next()){
                    ProducerData actualProducer = new ProducerData(rs.getString("db_producer_name"), rs.getString("db_producer_email"), rs.getString("db_producer_phone"));
                        rwproducers.add(actualProducer);
                }
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getAllProducers", "Lekérdezéshiba:\n"+e);
        }
        return rwproducers;
}
    
     public ArrayList<DispoData> getAllDisponents(){ 
        String query = "SELECT * FROM disposition";  
        ArrayList<DispoData> rwdisponents = null;
            
            try{
                
                ResultSet rs = createStmt.executeQuery(query);
                rwdisponents = new ArrayList<>();
                while (rs.next()){
                    DispoData actualDisponent = new DispoData(rs.getString("db_disp_name"), rs.getString("db_disp_email"), rs.getString("db_disp_phone"));
                        rwdisponents.add(actualDisponent);
                }
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getAllDisponents", "Lekérdezéshiba:\n"+e);
        }
        return rwdisponents;
}
         
     
    public ArrayList<String> getItemForCB(String fquery, String req_item){
            
        Set<String> filtereddata = new HashSet<>(); //...ugye mindenből csak egy példányra van szükségünk.   
        ArrayList<String> filteredrecords = null;
            
            try{
                ResultSet rs = createStmt.executeQuery(fquery);
                
                while (rs.next()){
                    String actualData = rs.getString(req_item);
                        filtereddata.add(actualData);
                   
                }
                filteredrecords = new ArrayList<>(filtereddata);
                
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getItemForCB", "Lekérdezéshiba:\n"+e);
        }
         
        return filteredrecords;
}
    
    public String getSingleItem(String req_item, String fquery, String match_item){ //fullosan működik
            String actualItem = "none";
            
            try{
                preparedStmt = cntn.prepareStatement(fquery);   
                preparedStmt.setString(1, match_item);
                                
                ResultSet rs = preparedStmt.executeQuery(); //itt vigyázz, a createstatement (Statement) ide nem jó!
                
                while (rs.next()){
                    actualItem = rs.getString(req_item);
                                 }
                                
        } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "getSingleItem", "Lekérdezéshiba:\n"+e);
        }
         
        return actualItem;
        
    }
    
    public void prepareStatement(ReworkData rwrecords, String query){
                
        try{
         preparedStmt = cntn.prepareStatement(query);   
            preparedStmt.setString(1, rwrecords.getReworkazon());
            preparedStmt.setString(2, rwrecords.getClaimazon());
            preparedStmt.setString(3, rwrecords.getReworklocazon());
            preparedStmt.setString(4, rwrecords.getCostperhour());
            preparedStmt.setString(5, rwrecords.getPartno());
            preparedStmt.setString(6, rwrecords.getPartname());
            preparedStmt.setString(7, rwrecords.getChargeno());
            preparedStmt.setString(8, rwrecords.getQtyforrework());
            preparedStmt.setString(9, rwrecords.getSecperpiece());
            preparedStmt.setString(10, rwrecords.getReworkhours());
            preparedStmt.setString(11, rwrecords.getCalculatedcostshuf());
            preparedStmt.setString(12, rwrecords.getCalculatedcostseur());
            preparedStmt.setString(13, rwrecords.getDefectdescription());
            preparedStmt.setString(14, rwrecords.getDefectphoto());
            preparedStmt.setString(15, rwrecords.getIssuedatum());
            preparedStmt.setString(16, rwrecords.getDeadlinedatum());
            preparedStmt.setString(17, rwrecords.getUsernev());
            preparedStmt.setString(18, rwrecords.getProdareaazon());
            preparedStmt.setString(19, rwrecords.getProdareanev());
            preparedStmt.setString(20, rwrecords.getCostareaazon());
            preparedStmt.setString(21, rwrecords.getProducernev());
            preparedStmt.setString(22, rwrecords.getProduceremail());
            preparedStmt.setString(23, rwrecords.getProducertelefon());
            preparedStmt.setString(24, rwrecords.getDisponev());
            preparedStmt.setString(25, rwrecords.getDispoemail());
            preparedStmt.setString(26, rwrecords.getDispotelefon());
            preparedStmt.execute();
            } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "PreparedStatement", "ReworkData adatbázishiba:\n"+e);
    }
    }    
    
     public void addNewRecord(ReworkData rwrecords){
         
        String add_query = "insert into reworks (db_rpo, db_claimno, db_reworkloctext, db_costperhour, db_partno, db_partname," +
                       "db_batch, db_qty, db_secperpcs, db_hourperqty, db_calccosthuf, db_calccosteur," +
                       "db_defect, db_defectpic, db_picktoday, db_pickdeadline, db_current_user, db_prodareaid, db_prodareaname, db_costarea," +
                       "db_producername, db_produceremail, db_producerphone, db_disponame, db_dispoemail, db_dispophone) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
        prepareStatement(rwrecords,add_query);
       
}
    
    public void updateRecords(ReworkData rwrecords){
                 
         String update_query = "update reworks set db_rpo = ?, db_claimno = ?, db_reworkloctext = ?, db_costperhour = ?, db_partno = ?, db_partname = ?," +
                       "db_batch = ?, db_qty = ?, db_secperpcs = ?, db_hourperqty = ?, db_calccosthuf = ?, db_calccosteur = ?," +
                       "db_defect = ?, db_defectpic = ?, db_picktoday = ?, db_pickdeadline = ?, db_current_user = ?, db_prodareaid = ?, db_prodareaname = ?, db_costarea = ?," +
                       "db_producername = ?, db_produceremail = ?, db_producerphone = ?, db_disponame = ?, db_dispoemail = ?, db_dispophone = ? where db_keyid = ?";
            
         prepareStatement(rwrecords, update_query);
             
}
    
    public void addNewMaterial(MaterialData rwmaterials){
         
        String add_query = "insert into materials (db_mat_nr, db_mat_desc, db_production_prodnr, db_disp_name) values (?,?,?,?)";
                
        try{
         preparedStmt = cntn.prepareStatement(add_query);   
            preparedStmt.setString(1, rwmaterials.getPartno());
            preparedStmt.setString(2, rwmaterials.getPartname());
            preparedStmt.setString(3, rwmaterials.getProdareano());
            preparedStmt.setString(4, rwmaterials.getDisponame());
            preparedStmt.execute();
       } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "PrepareStatement", "MaterialData adatbázishiba:\n"+e);
    }
}
    
    public void addNewProdarea(ProductionData rwproduction){
        
        String add_query = "insert into production (db_production_prodnr, db_production_prodname, db_production_costnr, db_producer_name) values (?,?,?,?)";
         try{
         preparedStmt = cntn.prepareStatement(add_query);   
            preparedStmt.setString(1, rwproduction.getProdareano());
            preparedStmt.setString(2, rwproduction.getProdareaname());
            preparedStmt.setString(3, rwproduction.getCostareano());
            preparedStmt.setString(4, rwproduction.getProducername());
            preparedStmt.execute();
       } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "PrepareStatement", "ProductionData adatbázishiba:\n"+e);
    }
    }
    
    public void addNewProducer(ProducerData rwproducer){
        
        String add_query = "insert into producers (db_producer_name, db_producer_email, db_producer_phone) values (?,?,?)";
         try{
         preparedStmt = cntn.prepareStatement(add_query);   
            preparedStmt.setString(1, rwproducer.getProducername());
            preparedStmt.setString(2, rwproducer.getProduceremail());
            preparedStmt.setString(3, rwproducer.getProducerphone());
            
            preparedStmt.execute();
       } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "PrepareStatement", "ProductionData adatbázishiba:\n"+e);
    }
    }
    
    public void addNewDisponent(DispoData rwdisponent){
        
        String add_query = "insert into disposition (db_disp_name, db_disp_email, db_disp_phone) values (?,?,?)";
         try{
         preparedStmt = cntn.prepareStatement(add_query);   
            preparedStmt.setString(1, rwdisponent.getDisponame());
            preparedStmt.setString(2, rwdisponent.getDispoemail());
            preparedStmt.setString(3, rwdisponent.getDispophone());
            
            preparedStmt.execute();
       } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "PrepareStatement", "DispoData adatbázishiba:\n"+e);
    }
    }
    
    public void dropTable(String selectedtable){
         try{   
            createStmt.execute("drop table "+selectedtable); //Aktiváld, ha változtatsz, különben nem lesz oszlopod az új rekordnak.
           
       } catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "CreateStatement", "Tábla "+selectedtable+" nem törölhető!:\n"+e);
    }
         msg.showAlertBox(Alert.AlertType.WARNING , "FIGYELEM!", "Tábla "+selectedtable+" törölve!");
    }
    
    public DataBase(){
           
      try{
            cntn = DriverManager.getConnection(URL);
            msg.showAlertBox(Alert.AlertType.INFORMATION, "getConnection DataBase", "Adatbáziskapcsolat létrejött:\n"+URL);
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
            
            ResultSet rsrew = dbmetadata.getTables(null,"APP", "REWORKS", null);
            if (!rsrew.next()){
            createStmt.execute("create table reworks(db_keyid INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), db_rpo varchar(13), db_claimno varchar(12), db_reworkloctext varchar(30), "
                    + "db_costperhour varchar(4), db_partno varchar(11), db_partname varchar(50), db_batch varchar(10), db_qty varchar(6), db_secperpcs varchar(3), db_hourperqty varchar(3), db_calccosthuf varchar(6), "
                    + "db_calccosteur varchar(4), db_defect varchar(255), db_defectpic varchar(255), db_picktoday varchar(10), db_pickdeadline varchar(10), db_current_user varchar(30), db_prodareaid varchar(6), db_prodareaname varchar(64), db_costarea varchar(6), "
                    + "db_producername varchar(30), db_produceremail varchar(30), db_producerphone varchar(10), db_disponame varchar(30), db_dispoemail varchar(30), db_dispophone varchar(10))");
            }
            
            ResultSet rsmat = dbmetadata.getTables(null,"APP", "MATERIALS", null);
            if (!rsmat.next()){
            createStmt.execute("create table materials(db_mat_keyid INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), db_mat_nr varchar(11), db_mat_desc varchar(128), db_production_prodnr varchar(6), db_disp_name varchar(30))");
            }
            
            ResultSet rsproducers = dbmetadata.getTables(null,"APP", "PRODUCERS", null);
            if (!rsproducers.next()){
            createStmt.execute("create table producers(db_producer_keyid INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), db_producer_name varchar(30), db_producer_email varchar(30), db_producer_phone varchar(10))");
            }
            
            ResultSet rsdisp = dbmetadata.getTables(null,"APP", "DISPOSITION", null);
            if (!rsdisp.next()){
            createStmt.execute("create table disposition(db_disp_keyid INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), db_disp_name varchar(30), db_disp_email varchar(30), db_disp_phone varchar(10))");
            }
            
            ResultSet rsproduction = dbmetadata.getTables(null,"APP", "PRODUCTION", null);
            if (!rsproduction.next()){
            createStmt.execute("create table production(db_production_keyid INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), db_production_prodnr varchar(6), db_production_prodname varchar(30), db_production_costnr varchar(6), db_producer_name varchar(30))");
            }
            
            }catch (SQLException e){
            msg.showAlertBox(Alert.AlertType.ERROR, "ResultSet", "Adatbázishiba:\n"+e);
        }
    
    }

    
}
