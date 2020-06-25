/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reworkfx;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author VERESG
 */
public class DateCallFuncs {
    
    private String currentyear, currentdate, yearnow, yearbefore;
    
    public DateCallFuncs(){
       Date current_year = new Date(); 
       Date current_date = new Date();
       SimpleDateFormat yearformat = new SimpleDateFormat("yyyy");
       SimpleDateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd  hh:mm");
       this.currentyear = yearformat.format(current_year); 
       this.currentdate = dateformat.format(current_date); 
       Calendar calendar = new GregorianCalendar();
       int year_now = calendar.get(Calendar.YEAR);
       calendar.add(Calendar.YEAR, -1);
       int year_before = calendar.get(Calendar.YEAR);
       this.yearnow = ""+year_now;
       this.yearbefore = ""+year_before;
       
    }

    public String getCurrentyear() {
        return currentyear;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public String getYearnow() {
        return yearnow;
    }

    public String getYearbefore() {
        return yearbefore;
    }
    
    
    
    
}
