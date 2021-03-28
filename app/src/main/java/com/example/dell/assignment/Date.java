package com.example.dell.assignment;


import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Dell on 11-02-2018.
 */

public class Date {

    Calendar calendar;

    public Date() {
        calendar = Calendar.getInstance();
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(calendar.getTime());
    }
    public int getOnlyDate(){
        return calendar.get(Calendar.DATE);
    }
    public int getOnlyMonth(){
        return calendar.get(Calendar.MONTH);
    }
    public int getOnlyYear(){
        return calendar.get(Calendar.YEAR);
    }
}