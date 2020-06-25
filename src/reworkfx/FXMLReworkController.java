/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reworkfx;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author VERESG
 */

public class FXMLReworkController implements Initializable {

    
    @FXML
    private AnchorPane AnchorPane_Main, anchorpane_TableView;
    @FXML
    private TableView TableView_QuickData, TableView_ReworkData, TableView_Masterdata;
    @FXML
    private Pane Pane_TableView, Pane_MasterDataView, Pane_NewRework, Pane_ListViewOptions, Pane_AddNewPartNo, Pane_AddNewMaster, Pane_AddNewDispo, Pane_AddNewProdArea, Pane_AddNewUser;
    @FXML
    private TitledPane TitledPane_QuickView, TitledPane_EnablePartDelivery;
    @FXML
    private ImageView ImageView_QuickView, ImageView_DefectPhoto1, ImageView_DefectPhoto2, imgView_NewUserPic;
    @FXML
    private AnchorPane Pane_TreeView;
    @FXML
    private StackPane StackPane_TreeView;
    @FXML
    private Label label, Label_DateTime;
    @FXML
    private Button Button_UploadDefectPhoto1, Button_UploadDefectPhoto2, Button_Refresh, Button_NewPartNo, Button_EnlargeImage, Button_NewReworkFromTemp, Button_RecordNewRework, Button_SaveNewMaterial, Button_CancelNewMaterial, Button_SaveNewProdArea, Button_SaveNewProducer, Button_SaveNewDispo;
    @FXML
    private Button btnSaveNewUser, btnCancelNewUser, btn_PicNewUser;
    @FXML
    private RadioButton RadioButton_Inside, RadioButton_Outside;
    @FXML
    private TreeView TreeView_LeftMenu;
    @FXML
    private ComboBox CB_NewRework_PartNo, ComboBox_Value, ComboBox_Data, ComboBox_NewPart_ProdArea, ComboBox_NewPart_DispoName, ComboBox_NewProdArea_Master, ComboBox_MasterData;
    @FXML
    private DatePicker DatePicker_NewRework_Today, DatePicker_NewRework_Deadline;
    @FXML
    private TextField TextField_NewRework_RPO, TextField_NewRework_ClaimNo, TextField_NewRework_PartName, TextField_NewRework_Batch, TextField_NewRework_MaxWorkDays;
    @FXML
    private TextField TextField_NewRework_Qty, TextField_NewRework_PcsPerSec, TextField_NewRework_HourPerQty, TextField_NewRework_CalcCostHuf, TextField_NewRework_CalcCostEur, TextField_NewRework_PriceUnitHUF, TextField_NewRework_PriceUnitEUR, TextField_NewRework_Defect; 
    @FXML
    private TextField TextField_NewRework_CostPerHour, TextField_TableView_CountOfRecords; 
    @FXML
    private TextField TextField_NewPart_DrawingNo, TextField_NewPart_MatDesc, TextField_NewPart_ProdName, TextField_NewPart_CostArea, TextField_NewPart_MasterName; //Új anyag felvitele
    @FXML
    private TextField TextField_NewMaster_Name, TextField_NewMaster_Email, TextField_NewMaster_Phone, TextField_NewDispo_Name, TextField_NewDispo_Email, TextField_NewDispo_Phone, TextField_NewProdArea_Code, TextField_NewProdArea_Desc, TextField_NewProdArea_CostAreaId; //Új mester, diszponens, gyártóterület felvitele
    @FXML
    private TextField TextField_ReworkID, TextField_PartID, TextField_PartName, TextField_ABNo, TextField_DefectDesc, TextField_LocationID; //Quickview controlok
    @FXML
    private TextField txtField_NewUser_ID, txtField_NewUser_Name, txtField_NewUser_Email;
    @FXML
    private PasswordField pswField_NewUser_Password;
    @FXML
    private Label Label_ReworkID, Label_PartID, Label_PartName, Label_ABNo, Label_DefectDesc, Label_LocationID; //Quickview controlok, nem használom
    @FXML
    private TextArea TextArea_Notes, TextArea_NewRework_Instructions;
    @FXML
    private CheckBox CheckBox_EnablePartDelivery, CheckBox_EnableNotes, CheckBox_ListAll;
    @FXML
    private MenuItem MenuItem_Exit, MenuItem_NewPartNo, MenuItem_NewMaster, MenuItem_NewDispo, MenuItem_NewProdArea, MenuItem_ViewMasterData, MenuItem_DeleteMasterTables;
    @FXML
    private MenuItem mnuItem_NewUser, mnuItem_BrowseUsers, mnuItem_DeleteUser, mnuItemDeleteUsersTable;
    
    private final String MENUA_ORDERS = "Megrendelések";
    private final String MENUA_NEW = "Új megrendelés";
    private final String MENUA_SEARCH = "Megrendelés keresése";
    private final String MENUA_LIST = "Megrendelések listája";
    
    private final String MENUB_ACTIONS = "Műveletek";
    private final String MENUB_SEND = "Megrendelés elküldése";
    private final String MENUB_EXPORT = "Exportálás fájlba";
    private final String MENUB_PRINT = "Nyomtatás";
    
    private final String ORDERS_MAIN_IMG = "/img/cmain.png";
    private final String NEW_IMG = "/img/new.png";
    private final String SEARCH_IMG = "/img/search.png";
    private final String LIST_IMG = "/img/list.png";
    private final String ACTIONS_MAIN_IMG = "/img/actions.jpg";
    private final String SENDMAIL_IMG = "/img/email.jpg";
    private final String EXP_EXC_IMG = "/img/excel.jpg";
    private final String PRINT_IMG = "/img/print.png";
    
    private final String OK_BUTTON_IMG = "/img/btn_login.png"; 
    private final String CANCEL_BUTTON_IMG = "/img/btn_cancel.png";

    private final int HOURPRICE_EXTERNAL = 1500;
    private final int HOURPRICE_INTERNAL = 1700;
    private int selected_hourprice = 1500; //default a külső utómunka óradíj
    private final int PRICEEUR = 349;
    private final double PRICEHUFPERPIECE = 0.417;
    
    private int rid_number_autoinc = 0; //kell ez?
            
    private String title, message, reworkloctext;
    
    private String current_user = System.getProperty("user.name");
    private String hibakep1 = ""; private String hibakep2 = "";
    private String defectimg1_path = ""; private String defectimg2_path = "";
    private String userimg_path = "";
    
    private String current_rid_dir = ""; //létrehozza a könyvtárat az aktuális utómunka megrendeléshez
    private String selectedMaterial = null; //NewRework anyaglista combobox selecteditem
    private String deletethistable = "";
       
    Dialogs msg = new Dialogs();
    File dirfile_rid = new File("C:\\ReworkFx\\RPO_ZM38");
    
    private Background bkgforbuttons = new Background(new BackgroundFill(Color.AQUA, null, null));
    
    Node nodepic; String picfile;
               
    private final ObservableList<ReworkData> reworkdata = FXCollections.observableArrayList();
    private final ObservableList<MaterialData> materialdata = FXCollections.observableArrayList();
    private final ObservableList<ProductionData> productiondata = FXCollections.observableArrayList();
    private final ObservableList<ProducerData> producerdata = FXCollections.observableArrayList();
    private final ObservableList<DispoData> dispodata = FXCollections.observableArrayList();
    private final ObservableList<UserData> userdata = FXCollections.observableArrayList();
    
    private final ObservableList<String> combo_data_List = FXCollections.observableArrayList();
    private final ObservableList<String> combo_table_List = FXCollections.observableArrayList();
            
    DataBase rwdb = new DataBase();
    UserDB usrdb = new UserDB();
    private String dbdata = ""; private String selectedValue = "";
    
    //--------------Lekérdezések-------------------------------
    private final String QUERY_ALL = "SELECT * FROM reworks";
    private String selected_query = "SELECT * FROM reworks WHERE ? = ?";
    private final String QUERY_MATERIAL = "SELECT * FROM materials";
    private final String QUERY_PRODUCER = "SELECT * FROM producers";
    private final String QUERY_PRODUCTION = "SELECT * FROM production";
    private final String QUERY_DISPOSITION = "SELECT * FROM disposition";
    
         
    //--------------Táblaoszlopok létrehozása és beállítása------------------------------
    private TableColumn addTableColumn(String colTitle, int minWidth, String cellValue){
        
        TableColumn colName = new TableColumn(colTitle); colName.setMinWidth(minWidth); colName.setCellFactory(TextFieldTableCell.forTableColumn()); colName.setCellValueFactory(new PropertyValueFactory<ReworkData, String>(cellValue));
        return colName;
    }

    private TableColumn addUserTableColumn(String colTitle, int minWidth, String cellValue){
        
        TableColumn colName = new TableColumn(colTitle); colName.setMinWidth(minWidth); colName.setCellFactory(TextFieldTableCell.forTableColumn()); colName.setCellValueFactory(new PropertyValueFactory<UserData, String>(cellValue));
        return colName;
    }
    
    public void setUserTableViewData(){
        
        Pane paneBrowseUsers = new Pane();
        TableView tblvBrowseUsers = new TableView();
        TableColumn tblcUserID = addUserTableColumn("Felhasználó ID", 100, "userid");
        TableColumn tblcUserName = addUserTableColumn("Felhasználónév", 200, "username");
        TableColumn tblcUserEmail = addUserTableColumn("E-mail", 200, "useremail");
        tblvBrowseUsers.getColumns().addAll(tblcUserID, tblcUserName, tblcUserEmail);
        userdata.addAll(usrdb.getUsers());
        tblvBrowseUsers.setItems(userdata);
        paneBrowseUsers.getChildren().add(tblvBrowseUsers);
        //anchorpane_TableView.getChildren().add(paneBrowseUsers);
        addNewActor(paneBrowseUsers, 500.0, 400.0, "Felhasználók listázása", true);
        
    }
    
    public void setTableViewData(){ //Ha a TableView -ben valamelyik adatot nem mutatja, akkor nem egyezik SingleStringProperty (pl. itt "disponev", a DispoData osztályban pedig "disponame"!!
         //ReworkData
         TableColumn reworkIDCol = addTableColumn("Megrendelés azonosító", 100, "reworkazon");
         TableColumn claimIDCol = addTableColumn("Reklamáció azonosító", 100, "claimazon");
         TableColumn reworkLocationCol = addTableColumn("Utómunka helye", 100, "reworklocazon");
         TableColumn costPerHourCol = addTableColumn("Óradíj", 50, "costperhour");
         TableColumn partIDCol = addTableColumn("Rajzszám", 100, "partno");
         TableColumn partNameCol = addTableColumn("Megnevezés", 200, "partname");
         TableColumn chargeNoCol = addTableColumn("Sarzs-szám", 100, "chargeno");
         TableColumn quantityCol = addTableColumn("Mennyiség", 50, "qtyforrework");
         TableColumn secPerPieceCol = addTableColumn("Darabidő", 50, "secperpiece");
         TableColumn reworkHoursCol = addTableColumn("Óraszám", 50, "reworkhours");
         TableColumn calculatedCostHufCol = addTableColumn("Számított költség (HUF)", 100, "calculatedcostshuf");
         TableColumn calculatedCostEurCol = addTableColumn("Számított költség (EUR)", 100, "calculatedcostseur");
         TableColumn defectDescCol = addTableColumn("Hibaleírás", 200, "defectdescription");
         TableColumn defectPhotoCol = addTableColumn("Hibakép", 200, "defectphoto");
         TableColumn issueDateCol = addTableColumn("Megrendelés dátuma", 100, "issuedatum");
         TableColumn deadlineDateCol = addTableColumn("Határidő", 100, "deadlinedatum");
         TableColumn userNameCol = addTableColumn("Ügyintéző", 100, "usernev");
         
         TableColumn prodareaNoCol = addTableColumn("Gyártóterület ID", 100, "prodareano");
         TableColumn prodareaNameCol = addTableColumn("Gyártóterület neve", 100, "prodareaname");
         TableColumn costareaNoCol = addTableColumn("Költséghely", 100, "costareano");
         
         TableColumn producerNameCol = addTableColumn("Mester neve", 200, "producername");
         TableColumn producerEmailCol = addTableColumn("Mester e-mail címe", 200, "produceremail");
         TableColumn producerPhoneCol = addTableColumn("Mester telefonszáma", 100, "producerphone");
         
         TableColumn dispoNameCol = addTableColumn("Diszponens neve", 200, "disponame");
         TableColumn dispoEmailCol = addTableColumn("Diszponens e-mail címe", 200, "dispoemail");
         TableColumn dispoPhoneCol = addTableColumn("Diszponens telefonszáma", 100, "dispophone");
         
         combo_data_List.addAll(partIDCol.getText(), reworkLocationCol.getText(), userNameCol.getText(), prodareaNoCol.getText(), costareaNoCol.getText(), producerNameCol.getText(), dispoNameCol.getText()); //ArrayList a listanézethez, ComboBox_Data
         
         TableView_ReworkData.getColumns().addAll(reworkIDCol, claimIDCol, reworkLocationCol, costPerHourCol, partIDCol, partNameCol, chargeNoCol, quantityCol, secPerPieceCol,  
                 reworkHoursCol, calculatedCostHufCol, calculatedCostEurCol, defectDescCol, defectPhotoCol, issueDateCol, deadlineDateCol, userNameCol, prodareaNoCol, prodareaNameCol, costareaNoCol, producerNameCol, producerEmailCol, producerPhoneCol, dispoNameCol, dispoEmailCol, dispoPhoneCol);
         
        reworkdata.addAll(rwdb.getAllRecords(QUERY_ALL));
        TableView_ReworkData.setItems(reworkdata);
            
        //--------------------------------------------------------------------------------------------------    
        ComboBox_Data.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
             
            CheckBox_ListAll.setSelected(false);
            ComboBox_Value.getItems().clear();
            
            String selectedData = ComboBox_Data.getSelectionModel().getSelectedItem().toString();
                switch (selectedData){
                    case "Rajzszám": dbdata = "db_partno"; 
                        break;
                    case "Utómunka helye": dbdata = "db_reworkloctext"; 
                        break;    
                    case "Ügyintéző": dbdata = "db_current_user"; 
                        break;
                    case "Gyártóterület ID": dbdata = "db_prodareaid"; 
                        break;
                    case "Költséghely": dbdata = "db_costarea"; 
                        break;    
                    case "Mester neve": dbdata = "db_producername"; 
                        break;        
                    case "Diszponens neve": dbdata = "db_disponame"; 
                        break;   
            }
            ComboBox_Value.getItems().addAll(rwdb.getItemForCB(QUERY_ALL, dbdata));
            }
        });
        //---------------------------------------------------------------------------------------------------    
        ComboBox_Value.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldvalue, Object newvalue){    
            
            String selectedValue = ComboBox_Value.getSelectionModel().getSelectedItem().toString();
            reworkdata.clear();
            
            reworkdata.addAll(rwdb.getAllRecords(dbdata, selectedValue));
            
            System.out.println(dbdata+ " "+selectedValue);
            TableView_ReworkData.setItems(reworkdata);
            }
        }); 
        
        CheckBox_ListAll.setOnAction(action -> {
            TableView_ReworkData.getItems().clear();
            if (CheckBox_ListAll.isSelected()){
                reworkdata.addAll(rwdb.getAllRecords(QUERY_ALL));
                TableView_ReworkData.setItems(reworkdata);
            }
        });
         
         
                          
         TableView_ReworkData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
                
                TablePosition pos = (TablePosition) TableView_ReworkData.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                
                Object selectedRow = TableView_ReworkData.getItems().get(row);
                
                TableColumn col = pos.getTableColumn(); //ez az alap, ha cellaértéket akarsz kiválasztani...

                // this gives the value in the selected cell:
                String reworkid = (String) reworkIDCol.getCellObservableValue(selectedRow).getValue(); //....de nekünk a kiválasztott sorok bizonyos oszlopainak értéke kell
                String partid = (String) partIDCol.getCellObservableValue(selectedRow).getValue();
                String partname = (String) partNameCol.getCellObservableValue(selectedRow).getValue();
                String abnumber = (String) chargeNoCol.getCellObservableValue(selectedRow).getValue();
                String defectdesc = (String) defectDescCol.getCellObservableValue(selectedRow).getValue();
                String defectpic = (String) defectPhotoCol.getCellObservableValue(selectedRow).getValue();
                String locationid = (String) reworkLocationCol.getCellObservableValue(selectedRow).getValue();
                                
         //Quickview controlok      
         Label_ReworkID.setText(reworkIDCol.getText()); Label_PartID.setText(partIDCol.getText()); Label_PartName.setText(partNameCol.getText()); Label_DefectDesc.setText(defectDescCol.getText()); Label_LocationID.setText(reworkLocationCol.getText());
         TextField_ReworkID.setText(reworkid); TextField_PartID.setText(partid); TextField_PartName.setText(partname); TextField_ABNo.setText(abnumber); TextField_DefectDesc.setText(defectdesc); TextField_LocationID.setText(locationid);
         ImageView_QuickView.setImage(new Image((defectpic))); 
         }
         });
        
        ComboBox_MasterData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){ //Törzsadatok megjelenítése
            public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
                
                Pane_MasterDataView.setVisible(true);
                
                String selectedTable = ComboBox_MasterData.getSelectionModel().getSelectedItem().toString();
            
            switch (selectedTable){
                
                case "Megrendelések" : TableView_Masterdata.getColumns().clear();  reworkdata.clear(); TableView_Masterdata.getColumns().addAll(reworkIDCol,claimIDCol, partIDCol, partNameCol, prodareaNoCol, dispoNameCol); reworkdata.addAll(rwdb.getAllRecords("SELECT * FROM reworks")); TableView_Masterdata.setItems(reworkdata);
                break;
                case "Anyagok" : TableView_Masterdata.getColumns().clear();  materialdata.clear(); TableView_Masterdata.getColumns().addAll(partIDCol, partNameCol, prodareaNoCol, dispoNameCol); materialdata.addAll(rwdb.getAllMaterials()); TableView_Masterdata.setItems(materialdata);
                break;
                case "Mesterek" : TableView_Masterdata.getColumns().clear();  producerdata.clear(); TableView_Masterdata.getColumns().addAll(producerNameCol, producerEmailCol, producerPhoneCol); producerdata.addAll(rwdb.getAllProducers()); TableView_Masterdata.setItems(producerdata);
                break;
                case "Diszponensek" : TableView_Masterdata.getColumns().clear();  dispodata.clear(); TableView_Masterdata.getColumns().addAll(dispoNameCol, dispoEmailCol, dispoPhoneCol); dispodata.addAll(rwdb.getAllDisponents()); TableView_Masterdata.setItems(dispodata);
                break;
                case "Gyártóterületek" : TableView_Masterdata.getColumns().clear(); productiondata.clear(); TableView_Masterdata.getColumns().addAll(prodareaNoCol, prodareaNameCol, costareaNoCol, producerNameCol);  productiondata.addAll(rwdb.getAllProdareas()); TableView_Masterdata.setItems(productiondata);
                break;
            }
            
            }
        }); 
         
         
    }
    
    public void recordNewRework(){
       
        //current_rid_dir ="";
        if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!","Utómunka rögzítése?") == true){
           selectedMaterial = CB_NewRework_PartNo.getSelectionModel().getSelectedItem().toString();
               ReworkData reworkData = new ReworkData("RPO"+TextField_NewRework_RPO.getText(), TextField_NewRework_ClaimNo.getText(), reworkloctext,TextField_NewRework_CostPerHour.getText(),selectedMaterial, TextField_NewRework_PartName.getText(), 
                       TextField_NewRework_Batch.getText(), TextField_NewRework_Qty.getText(),TextField_NewRework_PcsPerSec.getText(),TextField_NewRework_HourPerQty.getText(),TextField_NewRework_CalcCostHuf.getText(),TextField_NewRework_CalcCostEur.getText(),
                       TextField_NewRework_Defect.getText(), defectimg1_path, DatePicker_NewRework_Today.getValue().toString(), DatePicker_NewRework_Deadline.getValue().toString(), current_user, 
                       "prid", "prodname", "cano", "Mester neve", "mester.neve@te.com", "Mester tel", "Virág Valéria", "dispo.neve@te.com", "Dispo tel");
               
               reworkdata.addAll(reworkData);
               rwdb.addNewRecord(reworkData);
               
               //2020.06.23. //String current_rid_filename = TextField_NewRework_RPO.getText().replaceAll("/", "_"); //ez a fájlnév miatt szükséges: a "x/évszám" nem megengedett karaktert tartalmaz
               current_rid_dir = "Utómunka megrendelés_"+TextField_NewRework_ClaimNo.getText()+"_"+selectedMaterial+"_Batch_"+TextField_NewRework_Batch.getText()+"_RPO_"+TextField_NewRework_RPO.getText();
               
               File dirfile_rw = new File("C:\\ReworkFx\\RPO_ZM38\\"+current_rid_dir);
               
               if (dirfile_rw.mkdir()){
                   
                   msg.showMessage(null, "UM könyvtár létrehozva: "+dirfile_rw.toString(), "Információ", null);
                   
               }
               else {
                   msg.showMessage(null, "UM könyvtár nincs létrehozva: "+dirfile_rw.toString(), "Hiba!", null);
               }
    }
       
    }   
    
    public void addNewMaterial(){
       
        String selectedProdArea = ComboBox_NewPart_ProdArea.getSelectionModel().getSelectedItem().toString();
        String selectedDispoName = ComboBox_NewPart_DispoName.getSelectionModel().getSelectedItem().toString();
        
        if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!","Új anyag rögzítése?") == true){
            MaterialData materialData = new MaterialData(TextField_NewPart_DrawingNo.getText(), TextField_NewPart_MatDesc.getText(), selectedProdArea, selectedDispoName);
                materialdata.addAll(materialData);
                rwdb.addNewMaterial(materialData);
                
                               
    }
        
    }   
    
    public void addNewProduction(){
        String selectedProducerName = ComboBox_NewProdArea_Master.getSelectionModel().getSelectedItem().toString();                
        if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!","Új gyártóterület rögzítése?") == true){
            ProductionData prodareaData = new ProductionData(TextField_NewProdArea_Code.getText(), TextField_NewProdArea_Desc.getText(), TextField_NewProdArea_CostAreaId.getText(), selectedProducerName);
                productiondata.addAll(prodareaData);
                rwdb.addNewProdarea(prodareaData);
        }
    }
    
    
    public void addNewProducer(){
                
        if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!","Új mester rögzítése?") == true){
            ProducerData producerData = new ProducerData(TextField_NewMaster_Name.getText(), TextField_NewMaster_Email.getText(), TextField_NewMaster_Phone.getText());
                producerdata.addAll(producerData);
                rwdb.addNewProducer(producerData);
        }
    }
    
    public void addNewDispo(){
                
        if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!","Új diszponens rögzítése?") == true){
            DispoData dispoData = new DispoData(TextField_NewDispo_Name.getText(), TextField_NewDispo_Email.getText(), TextField_NewDispo_Phone.getText());
                dispodata.addAll(dispoData);
                rwdb.addNewDisponent(dispoData);
        }
    }
    
    public void addNewUser(){
                        
        if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!","Új felhasználó rögzítése?") == true){
            UserData userData = new UserData(txtField_NewUser_ID.getText(), txtField_NewUser_Name.getText(), txtField_NewUser_Email.getText(), pswField_NewUser_Password.getText());
                userdata.addAll(userData);
                                
                              
                 if (usrdb.checkUserId(txtField_NewUser_ID.getText()) == false){
                            usrdb.addNewAppUser(userData);
                 }
              //} else {msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!", "Már van ilyen azonosító: "+usrdb.checkUserId("userdb_id"));}
                    
                    }           
        
    
    }
        
    public void browseTheAppUsers(){
        
    };
    
    //---------------------------------TreeView létrehozása a SplitPane bal oldali StackPane -jében---------------------------------
        
    public TreeItem<String> addNodeItem(String title, TreeItem<String> parent, Node nodeimage){
        TreeItem<String> nodeitem = new TreeItem<>(title);
        nodeitem.setGraphic(nodeimage);
        nodeitem.setExpanded(true);
        parent.getChildren().add(nodeitem);
        return nodeitem;
    }
    
    
    private Node addNodePic(String picfile){
    
    this.picfile = picfile;
    this.nodepic = new ImageView(new Image(getClass().getResourceAsStream(picfile)));
    return nodepic;
    }

    
    private void setTreeMenuList(){
        
                                
        TreeItem<String> treeItemRoot1 = new TreeItem<>("Megrendelések");
            TreeView<String> treeView = new TreeView<>(treeItemRoot1);
            treeView.setShowRoot(false);
                
                    Node newContactNodePic = addNodePic(NEW_IMG);
                    Node findContactNodePic = addNodePic(SEARCH_IMG); 
                    Node contactListNodePic = addNodePic(LIST_IMG); 
                    Node sendEmailNodePic = addNodePic(SENDMAIL_IMG); 
                    Node expToExcelNodePic = addNodePic(EXP_EXC_IMG); 
                    Node printNodePic = addNodePic(PRINT_IMG); 
                    
                    //CsinĂˇljunk Ăˇgakat az addNodeItem (makeBranch) metĂłdussal..(listaelemszĂ¶veg,szĂĽlĹ‘,kĂ©p)...vagy a kĂ©p helyett "null", ha nem akarsz kĂ©pet
                    TreeItem<String> nodeItemA = addNodeItem(MENUA_ORDERS,treeItemRoot1, new ImageView(new Image(getClass().getResourceAsStream(ORDERS_MAIN_IMG))));
                    TreeItem<String> nodeItemB = addNodeItem(MENUB_ACTIONS,treeItemRoot1, new ImageView(new Image(getClass().getResourceAsStream(ACTIONS_MAIN_IMG))));
                                     
                                                 addNodeItem(MENUA_NEW,nodeItemA, newContactNodePic);
                                                 addNodeItem(MENUA_SEARCH,nodeItemA, findContactNodePic);
                                                 addNodeItem(MENUA_LIST,nodeItemA, contactListNodePic);
                                                 
                                                 addNodeItem(MENUB_SEND,nodeItemB, sendEmailNodePic);
                                                 addNodeItem(MENUB_EXPORT,nodeItemB, expToExcelNodePic);
                                                 addNodeItem(MENUB_PRINT,nodeItemB, printNodePic);
                                     
                           
                    
                    StackPane_TreeView.getChildren().add(treeView);
        
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
                TreeItem<String> selectedItem = (TreeItem<String>) newvalue;
                    String selectedMenu = selectedItem.getValue();
                    String selectedParent = (selectedItem.getParent().getValue() != null ? selectedItem.getParent().getValue() : selectedItem.getValue());
                                        
                    if (selectedMenu != null){
                    switch (selectedMenu){
                        case MENUA_ORDERS: try {selectedItem.setExpanded(true);} catch (Exception ex){} break;
                        case MENUA_NEW: try {newReworkFunctions();} catch (Exception ex){} break;
                        case MENUA_SEARCH: try {} catch (Exception ex){} break;
                        case MENUA_LIST: try {listViewFunctions();} catch (Exception ex){} break;
                        case MENUB_ACTIONS: try {selectedItem.setExpanded(true);} catch (Exception ex){} break;
                        case MENUB_SEND: try {} catch (Exception ex){} break;
                        case MENUB_EXPORT: try {exportToPdfFunctions();} catch (Exception ex){} break;
                        case MENUB_PRINT: try {} catch (Exception ex){} break;
                    }
                }    
            }
        
    });
}
   
    public void disableAllPanes(){
        ArrayList<Pane> disabledpanes = new ArrayList<>();
        disabledpanes.add(Pane_TableView); disabledpanes.add(Pane_ListViewOptions); disabledpanes.add(Pane_NewRework); disabledpanes.add(Pane_MasterDataView);
        for (Pane pn: disabledpanes){pn.setDisable(true); pn.setVisible(false);} TitledPane_QuickView.setVisible(false);
    }
    
    public void enablePane(Pane panename){
        panename.setVisible(true); panename.setDisable(false);
    }
    
    
    private String saveDefectPhoto(String imagesrc, String imageout) {
    BufferedImage image = null;
    File srcfile = null;
    File outfile = null;

    //read image file
    try{
      srcfile = new File(imagesrc);
      image = ImageIO.read(srcfile);
    }catch(IOException e){
      System.out.println("Error: "+e+srcfile);
    }

    //write image
    try{
      outfile = new File(imageout);
      ImageIO.write(image, "jpg", outfile);
    }catch(IOException e){
      System.out.println("Error: "+e+outfile);
    }
    
    return outfile.toURI().toString();
 } 
    
    public void uploadDefectPhoto1(){
        
        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Képfájlok, *.jpg, *.png","*.jpg", "*.png"));
        File selectedfile = filechooser.showOpenDialog(new Stage()); 
        Image defectphoto1 = new Image(selectedfile.toURI().toString());
        String dbimage1 = saveDefectPhoto(selectedfile.getAbsolutePath(), "C:/ReworkFx/temp/img/"+defectphoto1.hashCode()+".jpg");
        ImageView_DefectPhoto1.setImage(defectphoto1); //Azért nem a mentett fájlnevet adom meg, mert utómunka mentése előtt még kicserélhetem, szóval ne gyártsunk feleslegesen sok képet
        ImageView_QuickView.setImage(new Image(dbimage1)); 
        defectimg1_path = dbimage1; //ezzel adjuk át a fájlhoz vezető útvonalat az adatbázisnak
        hibakep1 = defectimg1_path;
        
    }
    
     public void uploadDefectPhoto2(){
        
        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Képfájlok, *.jpg, *.png","*.jpg", "*.png"));
        File selectedfile = filechooser.showOpenDialog(new Stage()); 
        Image defectphoto2 = new Image(selectedfile.toURI().toString());
        String dbimage2 = saveDefectPhoto(selectedfile.getAbsolutePath(), "C:/ReworkFx/temp/img/"+defectphoto2.hashCode()+".jpg");
        hibakep2 = dbimage2;
        ImageView_DefectPhoto2.setImage(defectphoto2);
        defectimg2_path = dbimage2; //ezzel adjuk át a fájlhoz vezető útvonalat az adatbázisnak
        
    }
    
     public void uploadUserPhoto(ImageView imagewindow){
        
        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Képfájlok, *.jpg, *.png","*.jpg", "*.png"));
        File selectedfile = filechooser.showOpenDialog(new Stage()); 
        Image userphoto = new Image(selectedfile.toURI().toString());
        String userimg = saveDefectPhoto(selectedfile.getAbsolutePath(), "C:/ReworkFx/src/userimg/"+userphoto.hashCode()+".jpg");
        imagewindow.setImage(new Image(selectedfile.toURI().toString())); //imagewindow.setImage(userphoto);
        userimg_path = userimg; //ezzel adjuk át a fájlhoz vezető útvonalat az adatbázisnak
        
    }
     
    public void enlargePicture(Image image){
        Stage bigphotostage = new Stage();
        ImageView largeimgview = new ImageView(image);
        Pane bigphotopane = new Pane(); 
            
            largeimgview.fitHeightProperty().bind(bigphotostage.heightProperty()); 
            largeimgview.fitWidthProperty().bind(bigphotostage.widthProperty());
            largeimgview.setPreserveRatio(true);
            
        bigphotostage.setHeight(620); bigphotostage.setWidth(800);
        bigphotostage.setMinHeight(620); bigphotostage.setMinWidth(800);
               
            Double currentheight = largeimgview.getImage().getHeight();
            Double currentwidth =  largeimgview.getImage().getWidth();
        bigphotostage.setTitle("Hibakép nagyítása - "+"Eredeti méret: "+currentwidth.intValue()+" x "+currentheight.intValue());
        bigphotostage.initModality(Modality.APPLICATION_MODAL);
        bigphotopane.getChildren().add(largeimgview);
        Scene bigphotoscene = new Scene(bigphotopane);
        bigphotostage.setScene(bigphotoscene);
        bigphotostage.show();
    } 
    
    
    //Permanens könyvtárak létrehozása
    public void createDirectory(){
        
                      
               if (dirfile_rid.mkdir()){
                   
                   msg.showMessage(null, "Könyvtár létrehozva: "+dirfile_rid.toString(), "Információ", null);
                   
               }
               
               else if (dirfile_rid.exists()){
                   msg.showMessage(null, "Könyvtár már létezik: "+dirfile_rid.toString(), "Hiba!", null);
    }
               else {
                   msg.showMessage(null, "Könyvtár nincs létrehozva: "+dirfile_rid.toString(), "Hiba!", null);
               }
    }
    
    
    
    //Eseményfigyelők generálása
    public void addActionListeners(){
        
                
        mnuItem_NewUser.setOnAction(action -> {
            addNewActor(Pane_AddNewUser, 400.0, 280.0, "Új felhasználó rögzítése", false);
        });
        
        mnuItem_BrowseUsers.setOnAction(action -> {
        setUserTableViewData();
        });
        
        mnuItemDeleteUsersTable.setOnAction(action -> {
            usrdb.deleteUsersTable();
        });
        
        /*TextField_NewRework_RPO.textProperty().addListener((observable, oldValue, newValue)-> {
            TextField_NewRework_RPO.setText(oldValue+"/"+new DateCallFuncs().getCurrentyear());
            TextField_NewRework_ClaimNo.requestFocus();
        });*/
        
        TextField_NewRework_PcsPerSec.textProperty().addListener((observable, oldValue, newValue)-> {
            updateReworkValues();
        });
        
        RadioButton_Outside.setOnAction(action -> {
            TextField_NewRework_CostPerHour.setText(""+HOURPRICE_EXTERNAL);
            reworkloctext = RadioButton_Outside.getText();
            updateReworkValues();
        });
        
        RadioButton_Inside.setOnAction(action -> {
            TextField_NewRework_CostPerHour.setText(""+HOURPRICE_INTERNAL);
            reworkloctext = RadioButton_Inside.getText();
            updateReworkValues();
        });
        
        DatePicker_NewRework_Today.setOnAction(action -> {
            int maxworkdays = DatePicker_NewRework_Deadline.getValue().getDayOfYear() - DatePicker_NewRework_Today.getValue().getDayOfYear();
            if (maxworkdays < 0){msg.showAlertBox(AlertType.ERROR, "Hiba!", "A határidő korábbi dátumra esik, mint a megrendelés időpontja!"); 
                DatePicker_NewRework_Deadline.setValue(LocalDate.now()); DatePicker_NewRework_Today.setValue(LocalDate.now()); return;};
            TextField_NewRework_MaxWorkDays.setText(""+maxworkdays);
        });
        
        DatePicker_NewRework_Deadline.setOnAction(action -> {
            int maxworkdays = DatePicker_NewRework_Deadline.getValue().getDayOfYear() - DatePicker_NewRework_Today.getValue().getDayOfYear();
            if (maxworkdays < 0){msg.showAlertBox(AlertType.ERROR, "Hiba!", "A határidő korábbi dátumra esik, mint a megrendelés időpontja!"); 
                DatePicker_NewRework_Deadline.setValue(LocalDate.now()); DatePicker_NewRework_Today.setValue(LocalDate.now()); return;};
            TextField_NewRework_MaxWorkDays.setText(""+maxworkdays);
        });
                
        CB_NewRework_PartNo.setOnAction(action -> {
            String selected_partno = CB_NewRework_PartNo.getSelectionModel().getSelectedItem().toString();
            String nrwpartname = rwdb.getSingleItem("db_mat_desc", "SELECT db_mat_desc FROM materials WHERE db_mat_nr = ?", selected_partno);
            TextField_NewRework_PartName.setText(nrwpartname);
        });
        
        CheckBox_EnablePartDelivery.setOnAction(action -> {
            if (CheckBox_EnablePartDelivery.isSelected() == true) {TitledPane_EnablePartDelivery.setDisable(false);}
            else {TitledPane_EnablePartDelivery.setDisable(true);} 
        });
        
        CheckBox_EnableNotes.setOnAction(action -> {
            if (CheckBox_EnableNotes.isSelected() == true) {TextArea_Notes.setDisable(false);}
            else {TextArea_Notes.setDisable(true);} 
        });
        
        Button_NewPartNo.setOnMouseMoved(action -> {
            Button_NewPartNo.setBackground(bkgforbuttons);
        });
        
        Button_SaveNewMaterial.setOnAction(action -> {
            addNewMaterial();
            
        });
        
        Button_SaveNewProdArea.setOnAction(action -> {
            addNewProduction();
        });
        
        Button_SaveNewProducer.setOnAction(action -> {
            addNewProducer();
        });
        
        Button_SaveNewDispo.setOnAction(action -> {
            addNewDispo();
        });
        
        btnSaveNewUser.setOnAction(action -> {
            addNewUser();
        });
        
                
        Button_EnlargeImage.setOnMouseMoved(action -> {
            Button_EnlargeImage.setScaleX(1.2); Button_EnlargeImage.setScaleY(1.2);
        });
        
        Button_EnlargeImage.setOnMouseExited(action -> {
            Button_EnlargeImage.setScaleX(1.0); Button_EnlargeImage.setScaleY(1.0);
        });
        
        Button_EnlargeImage.setOnMouseClicked(action -> { 
            if (ImageView_QuickView.getImage()!= null){
            enlargePicture(ImageView_QuickView.getImage());
            }
        });
        
        Button_NewReworkFromTemp.setOnMouseMoved(action -> {
            Button_NewReworkFromTemp.setScaleX(1.2); Button_NewReworkFromTemp.setScaleY(1.2);
        });
        
        Button_NewReworkFromTemp.setOnMouseExited(action -> {
            Button_NewReworkFromTemp.setScaleX(1.0); Button_NewReworkFromTemp.setScaleY(1.0);
        });
        
        Button_NewReworkFromTemp.setOnMouseClicked(action -> { 
         //Új utómunka létrehozása meglévő megrendelés adataival
        });
                
        Button_RecordNewRework.setOnMouseMoved(action -> {
            Button_RecordNewRework.setBackground(bkgforbuttons);
        });
        
        Button_RecordNewRework.setOnMouseClicked(action -> {
            recordNewRework();
        });
        
        ImageView_DefectPhoto1.setOnMouseClicked(action -> {
            enlargePicture(ImageView_DefectPhoto1.getImage());
        });
        
        ImageView_DefectPhoto2.setOnMouseClicked(action -> {
            enlargePicture(ImageView_DefectPhoto2.getImage());
        });
        
        //------------------- Törzsadatok felvitele ------------------------------------
        MenuItem_NewPartNo.setOnAction(action -> {
            addNewActor(Pane_AddNewPartNo, 650.0, 280.0, "Új rajzszám hozzáadása", false);
            ComboBox_NewPart_ProdArea.getItems().addAll(rwdb.getItemForCB(QUERY_PRODUCTION, "db_production_prodnr"));
            ComboBox_NewPart_DispoName.getItems().addAll(rwdb.getItemForCB(QUERY_DISPOSITION, "db_disp_name"));
                    
            ComboBox_NewPart_ProdArea.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
                public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
                    String selected_prodarea = ComboBox_NewPart_ProdArea.getSelectionModel().getSelectedItem().toString();
                        String prodarea_prodname = rwdb.getSingleItem("db_production_prodname", "SELECT db_production_prodname FROM production WHERE db_production_prodnr = ?", selected_prodarea);
                        String prodarea_costarea = rwdb.getSingleItem("db_production_costnr", "SELECT db_production_costnr FROM production WHERE db_production_prodnr = ?", selected_prodarea);
                        String prodarea_producername = rwdb.getSingleItem("db_producer_name", "SELECT db_producer_name FROM production WHERE db_production_prodnr = ?", selected_prodarea);
                    TextField_NewPart_ProdName.setText(prodarea_prodname);
                    TextField_NewPart_CostArea.setText(prodarea_costarea);
                    TextField_NewPart_MasterName.setText(prodarea_producername);
        }
        
        });
        });
            
            
        MenuItem_NewMaster.setOnAction(action -> {
            addNewActor(Pane_AddNewMaster, 400.0, 280.0, "Új mester hozzáadása", false);
        });
        
        MenuItem_NewDispo.setOnAction(action -> {
            addNewActor(Pane_AddNewDispo, 500.0, 280.0, "Új diszponens hozzáadása", false);
        });
        
        MenuItem_NewProdArea.setOnAction(action -> {
            addNewActor(Pane_AddNewProdArea, 400.0, 300.0, "Új gyártóterület hozzáadása", false);
            ComboBox_NewProdArea_Master.getItems().addAll(rwdb.getItemForCB(QUERY_PRODUCER, "db_producer_name"));
            
        });
        
        //------------------- Törzsadatok táblanézete ------------------------------------
        MenuItem_ViewMasterData.setOnAction(action -> {
            disableAllPanes(); enablePane(Pane_MasterDataView);
            combo_table_List.addAll("Megrendelések", "Anyagok", "Mesterek", "Diszponensek", "Gyártóterületek");
            ComboBox_MasterData.getItems().addAll(combo_table_List);
                    
        });
    
         //------------------- Törzsadatok törlése - developer mode! ------------------------------------
        MenuItem_DeleteMasterTables.setOnAction(action -> {
                                
                Stage stage_droptable = new Stage();
                stage_droptable.setTitle("Tábla törlése");
                stage_droptable.setWidth(400); stage_droptable.setHeight(200);
                Pane pane_droptable = new Pane();
                Scene scene_droptable = new Scene(pane_droptable);
                stage_droptable.setScene(scene_droptable);
                ComboBox cbdroptable = new ComboBox();
                    cbdroptable.setLayoutX(10.0);
                Button okbutton = new Button("Törlés");
                    okbutton.setLayoutX(200.0); okbutton.setLayoutY(120.0);
                pane_droptable.getChildren().addAll(cbdroptable, okbutton);
                stage_droptable.show();
                        
                cbdroptable.getItems().addAll("Megrendelések", "Anyagok", "Mesterek", "Diszponensek", "Gyártóterületek");
                
                cbdroptable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){ //Törzsadatok megjelenítése
                    public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
                        String selecteddroptable = cbdroptable.getSelectionModel().getSelectedItem().toString();
            
                switch (selecteddroptable){
                
                case "Megrendelések" : deletethistable = "REWORKS";
                break;
                case "Anyagok" : deletethistable = "MATERIALS";
                break;
                case "Mesterek" : deletethistable = "PRODUCERS";
                break;
                case "Diszponensek" : deletethistable = "DISPOSITION";
                break;
                case "Gyártóterületek" : deletethistable = "PRODUCTION";
                break;
            }
            
            }
        }); 
                
                okbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent action) {
                        if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!","Biztosan törli a "+deletethistable+" táblát?") == true){
                            rwdb.dropTable(deletethistable);
                        }
                    }
                });
    });    
    }    
    //-------------------TreeView menüelem funkciók--------------------------------------
    public void newReworkFunctions(){
        disableAllPanes(); enablePane(Pane_NewRework);
        //CB_NewRework_PartNo.getItems().addAll(materialdata);
        CB_NewRework_PartNo.getItems().addAll(rwdb.getItemForCB(QUERY_MATERIAL, "db_mat_nr"));
        calculateReworkValues();
                    
    }
    
    public void calculateReworkValues(){
        rid_number_autoinc = (reworkdata.size()+1);
        //TextField_NewRework_RPO.setText(""+rid_number_autoinc+"/"+new DateCallFuncs().getCurrentyear()); //Módosítás 2020.06.23. - RID --> Rework PO számra
        
        try{
            int maxworkdays = DatePicker_NewRework_Deadline.getValue().getDayOfYear() - DatePicker_NewRework_Today.getValue().getDayOfYear();
            if (maxworkdays < 0){msg.showAlertBox(AlertType.ERROR, "Hiba!", "A határidő korábbi dátumra esik, mint a megrendelés időpontja!");};
            TextField_NewRework_MaxWorkDays.setText(""+maxworkdays);
            int quantity = (Integer.parseInt(TextField_NewRework_Qty.getText()));
            int secperpiece = (Integer.parseInt(TextField_NewRework_PcsPerSec.getText()));
            double hufperpiece = (secperpiece * 0.417);
            int totalhours = (quantity * secperpiece) / 3600;
            selected_hourprice = (RadioButton_Inside.isSelected() ? HOURPRICE_INTERNAL : HOURPRICE_EXTERNAL);
            int costinhuf = totalhours * selected_hourprice;
            TextField_NewRework_CostPerHour.setText(RadioButton_Inside.isSelected() ? ""+HOURPRICE_INTERNAL : ""+HOURPRICE_EXTERNAL);
            TextField_NewRework_HourPerQty.setText(""+totalhours);
            TextField_NewRework_CalcCostHuf.setText(""+costinhuf); TextField_NewRework_CalcCostEur.setText(""+costinhuf/PRICEEUR);
            TextField_NewRework_PriceUnitHUF.setText(""+hufperpiece);
            TextField_NewRework_PriceUnitEUR.setText(""+hufperpiece/PRICEEUR);
        }
        catch (Exception e){msg.showAlertBox(AlertType.ERROR, "Hiba!", "Hiba a mezők számításánál!");}
    }
    
        
    public void listViewFunctions(){
        disableAllPanes(); enablePane(Pane_TableView); enablePane(Pane_ListViewOptions);
        TitledPane_QuickView.setVisible(true); 
        TextField_TableView_CountOfRecords.setText(""+reworkdata.size());
                
        Button_EnlargeImage.setTooltip(msg.showToolTip("Kép nagyítása"));
        Button_NewReworkFromTemp.setTooltip(msg.showToolTip("Új utómunka létrehozása"));
    }
    
     public void updateReworkValues(){
        
        try{
            int quantity = (Integer.parseInt(TextField_NewRework_Qty.getText()));
            int secperpiece = (Integer.parseInt(TextField_NewRework_PcsPerSec.getText()));
            double hufperpiece = (secperpiece * PRICEHUFPERPIECE);
            int totalhours = (quantity * secperpiece) / 3600;
            selected_hourprice = (RadioButton_Inside.isSelected() ? HOURPRICE_INTERNAL : HOURPRICE_EXTERNAL);
            int costinhuf = totalhours * selected_hourprice;
            TextField_NewRework_CostPerHour.setText(RadioButton_Inside.isSelected() ? ""+HOURPRICE_INTERNAL : ""+HOURPRICE_EXTERNAL);
            TextField_NewRework_HourPerQty.setText(""+totalhours);
            TextField_NewRework_CalcCostHuf.setText(""+costinhuf); TextField_NewRework_CalcCostEur.setText(""+costinhuf/PRICEEUR);
            TextField_NewRework_PriceUnitHUF.setText(""+hufperpiece);
            TextField_NewRework_PriceUnitEUR.setText(""+hufperpiece/PRICEEUR);
            CB_NewRework_PartNo.getItems().addAll(rwdb.getItemForCB(QUERY_MATERIAL, "db_mat_nr"));
        
        }
        catch (Exception e){msg.showAlertBox(AlertType.ERROR, "Hiba!", "Hiba a mezők számításánál!");}
    }
    
    
    public void setDefaultValues(){//Csak egyszer akarjuk futtatni, a program indításakor, pl. feltöltjük a ComboBox listákat, stb.
        String thisyear = new DateCallFuncs().getYearnow();
        String lastyear = new DateCallFuncs().getYearbefore();
        ComboBox_Data.getItems().addAll(combo_data_List);
        DatePicker_NewRework_Deadline.setValue(LocalDate.now()); DatePicker_NewRework_Today.setValue(LocalDate.now());
    }
    
    
    //Külső funkciók---------------------------------------------------------
    private void exportToPdfFunctions(){
        
        //2020.06.23. //String mod_filename = TextField_NewRework_RPO.getText().replaceAll("/", "_"); //ez a fájlnév miatt szükséges: a "x/évszám" nem megengedett karaktert tartalmaz
        //2020.06.23. //String default_filename = mod_filename+".Utómunka megrendelés_"+selectedMaterial+"_Batch_"+TextField_NewRework_Batch.getText();
        
        String default_filename = "Utómunka megrendelés_"+TextField_NewRework_ClaimNo.getText()+"_"+selectedMaterial+"_Batch_"+TextField_NewRework_Batch.getText()+"_RPO_"+TextField_NewRework_RPO.getText();
        String default_filetitle = "Utómunka megrendelés_"+TextField_NewRework_ClaimNo.getText()+"_"+selectedMaterial+"_Batch_"+TextField_NewRework_Batch.getText()+"_RPO_"+TextField_NewRework_RPO.getText();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.BOLD);
        Font chapterFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 14, Font.BOLD);
        Font normalFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.NORMAL);
        
        
        Document pdfDocument = new Document();
        if (msg.showAlertBox(AlertType.CONFIRMATION,"Fájl létrehozása","Exportálás pdf fájlba?") == true) {
        try{
            FileChooser savefc = new FileChooser();
            savefc.setTitle("PDF fájl mentése másként");
            File newfile = savefc.showSaveDialog(new Stage()); //Mentés másként
                        
            FileOutputStream fos = new FileOutputStream("C:\\ReworkFx\\RPO_ZM38\\"+current_rid_dir+"\\"+default_filename + ".pdf"); //Alapból a SAP QN szám+Anyagszám+Batch-szám+RPO adja az input fájlnevet (pdffilename)
            PdfWriter.getInstance(pdfDocument, fos);
            pdfDocument.open();
            
            /*2020.06.23. com.itextpdf.text.Image defectimg1topdf = com.itextpdf.text.Image.getInstance("./src/rwdata/defectphoto1.jpg"); //Nem kell getClass().getResource(), különben ikszepsönt dob
            com.itextpdf.text.Image defectimg2topdf = com.itextpdf.text.Image.getInstance("./src/rwdata/defectphoto2.jpg");*/
            
            com.itextpdf.text.Image defectimg1topdf = com.itextpdf.text.Image.getInstance(hibakep1); //Nem kell getClass().getResource(), különben ikszepsönt dob
            com.itextpdf.text.Image defectimg2topdf = com.itextpdf.text.Image.getInstance(hibakep2);
            
            defectimg1topdf.scaleToFit(500, 400); defectimg2topdf.scaleToFit(500, 400);
            //defectimg1topdf.setAbsolutePosition(50f, 0f); defectimg2topdf.setAbsolutePosition(50f, 500f);
            
            pdfDocument.add(new Paragraph(default_filetitle,titleFont));
            pdfDocument.add(defectimg1topdf); pdfDocument.add(defectimg2topdf);
            
            pdfDocument.close();
            msg.showMessage(null, "Fájl sikeresen létrehozva: "+current_rid_dir+"\\"+default_filetitle + ".pdf", "Információ", null);
            
        }catch (Exception e){msg.showAlertBox(AlertType.ERROR, "Hiba!", "Hiba a fájl írásakor!\n"+"\n"+e);}
        finally {}
        }
    }    
    
    public void App_LogIn (){
        Stage stage_login = new Stage();
            stage_login.setTitle("Bejelentkezés");
            stage_login.setWidth(400); stage_login.setHeight(220);
        Pane pane_login = new Pane();
        Scene scene_login = new Scene(pane_login);
            stage_login.setScene(scene_login);
        
        Label psw_label = new Label("Jelszó");
            psw_label.setLayoutX(20); psw_label.setLayoutY(75);
        Label username_label = new Label("Felhasználónév");
            username_label.setLayoutX(20); username_label.setLayoutY(25);
        PasswordField psw_field = new PasswordField();
            psw_field.setScaleX(1.5); psw_field.setScaleY(1.5); 
            psw_field.setLayoutX(170); psw_field.setLayoutY(70);
        TextField username_txtfield = new TextField("Felhasználónév");
            username_txtfield.setScaleX(1.5); username_txtfield.setScaleY(1.5); 
            username_txtfield.setLayoutX(170); username_txtfield.setLayoutY(20);
        Button login_btn = new Button(); login_btn.setGraphic(addNodePic(OK_BUTTON_IMG));
            login_btn.setLayoutX(90); login_btn.setLayoutY(120);
        Button cancel_btn = new Button(); cancel_btn.setGraphic(addNodePic(CANCEL_BUTTON_IMG));
            cancel_btn.setLayoutX(230); cancel_btn.setLayoutY(120); 
        pane_login.getChildren().addAll(username_txtfield, psw_field, login_btn, cancel_btn, psw_label, username_label);
        //stage_login.show();
        
        //------------Eseményfigyelők a login ablakhoz--------------------------------------
        cancel_btn.setOnAction(action -> {
            stage_login.close();
        });
    }
    
    //Menü funkciók, login ablakot, vagy adatfelviteli ablakot hoz létre, az Actor itt a felhasználó, rajzszám, mester, diszponens, gyártóterület, stb.
    public void addNewActor(Pane panename, Double stagewidth, Double stageheight, String stagetitle, Boolean sizeable){
        Stage stagename = new Stage();
        panename.setVisible(true);
        Scene scenes = new Scene(new Pane(panename));
        panename.setLayoutX(0); panename.setLayoutY(0);
        
        Button btnCancelGeneral = new Button("Mégsem"); //"Mégsem" gomb az összes példányhoz, egyszerűbb, mint minden ablakhoz új gombot adni, és lekódolni :(
        Button btn_PicNewUser = new Button("Profilkép+");
        File imagefile = new File("c:/ReworkFx/src/userimg/contacts.png");
        ImageView imgView_userimg = new ImageView();
        imgView_userimg.setFitHeight(130); imgView_userimg.setFitWidth(100);
        Image userimage = new Image(imagefile.toURI().toString());
                imgView_userimg.setImage(userimage);
                        
        
        btnCancelGeneral.setLayoutX(panename.getWidth()-100.0);
        btnCancelGeneral.setLayoutY(panename.getHeight()-50.0);
        panename.getChildren().add(btnCancelGeneral);
        
        btn_PicNewUser.setLayoutX(panename.getWidth()-90.0);
        btn_PicNewUser.setLayoutY(panename.getHeight()-100.0);
        panename.getChildren().add(btn_PicNewUser);
        
        imgView_userimg.setLayoutX(panename.getWidth()-120.0);
        imgView_userimg.setLayoutY(panename.getHeight()-250.0);
        panename.getChildren().add(imgView_userimg);
        
        stagename.setWidth(stagewidth); stagename.setHeight(stageheight); 
        stagename.initModality(Modality.APPLICATION_MODAL);
        stagename.setTitle(stagetitle); 
        stagename.setResizable(sizeable);
        stagename.setScene(scenes);
        stagename.show();
        
        btnCancelGeneral.setOnAction(action -> { //Szépen bezárjuk az ablakot
            if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!", "Biztosan kilép?") == true){
                stagename.close();
            }
            
        });
        
        btn_PicNewUser.setOnAction(action -> {
        uploadUserPhoto(imgView_userimg);
        btn_PicNewUser.setText("Csere");
        });
    }

    //--------------System és user infók-----------------------
    public void showSystemInfo(){
        String osname = System.getProperty("os.name");
        String osver = System.getProperty("os.version");
        
        msg.showMessage(null, "Operációs rendszer: "+osname+"\n"+"Verzió: "+osver+"\n"+"Felhasználó: "+current_user, "Rendszer információk", null);
    }
    
    public void showProgInfo(){
        
        msg.showMessage(null, "Programverzió: v01.a\n"+"Coder: Gabor Veres\n"+"CopyRight - 2018 - DjRed's Software Dev. ", "Program információk", null);
    }
    
    public void setSysInfoLabels(){
        String datenow = new DateCallFuncs().getCurrentdate();
        Label_DateTime.setText(datenow);
    }
    
    public void exitTheProgram(){
        
            if (msg.showAlertBox(AlertType.CONFIRMATION,"Figyelem!", "Biztosan kilép?") == true){
                System.exit(0);
            }
            else return;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App_LogIn();
        
        setSysInfoLabels();
        setTreeMenuList();
        setTableViewData();
        addActionListeners();
        setDefaultValues();
        createDirectory();
        
    }    
    
}
