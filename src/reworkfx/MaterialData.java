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
public class MaterialData {
    
    private final SimpleStringProperty matkeyid, partno, partname, prodareano, disponame;
    
    public MaterialData(String materialNo, String materialDesc, String prodNr, String dispoName){
        
        this.matkeyid = new SimpleStringProperty("");
        this.partno = new SimpleStringProperty(materialNo);
        this.partname = new SimpleStringProperty(materialDesc);
        this.prodareano = new SimpleStringProperty(prodNr);
        this.disponame = new SimpleStringProperty(dispoName);
    }
    //Használd a Getter-eket, ha a TebleView-ben nem jelenít meg oszlopértékeket!! Gyakran elírod!
    public String getMatkeyid() {
        return matkeyid.get();
    }

    public String getPartno() {
        return partno.get();
    }

    public String getPartname() {
        return partname.get();
    }

    public String getProdareano() {
        return prodareano.get();
    }

    public String getDisponame() {
        return disponame.get();
    }
    
    

    public void setMatno(String materialNo) {
        partno.set(materialNo);
    }

    public void setMatdesc(String materialDesc) {
        partname.set(materialDesc);
    }
    
    public void setProdnr(String prodNr) {
        prodareano.set(prodNr);
    }

    public void setDisponame(String dispoName) {
        disponame.set(dispoName);
    }

      
    
}
