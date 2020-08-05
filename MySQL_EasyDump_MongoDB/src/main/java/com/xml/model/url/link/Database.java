/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.model.url.link;


public class Database {
    
    public Database(){}
    
    public Database(String guid,int day,int splite){
        this.guid=guid;
        this.day=day;
        this.splite=splite;
    }
    
    public Database(String guid){
        this.guid=guid;
    }
    
    
    private String guid="";
    private int day=7;
    private int splite=1;
    private int mark=0;//标记备份计划中是否做过修改
    

    public String getGUID() {
        return guid;
    }

    public void setGUID(String guid) {
        this.guid = guid;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    public int getSplite() {
        return splite;
    }


    public void setSplite(int splite) {
        this.splite = splite;
    }

    /**
     * @return the mark
     */
    public int getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public void setMark(int mark) {
        this.mark = mark;
    }
    
}
