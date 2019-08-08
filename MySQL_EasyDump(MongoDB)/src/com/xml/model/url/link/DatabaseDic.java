/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.model.url.link;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseDic {
    private Map<String,Database> databases=null;
    
    public void setDatabase(String dbname,Database db){
        if(databases==null){
            databases=new HashMap();
        }
        databases.put(dbname, db);
    }
    
    public Map<String,Database> getDatabases(){
        return databases;
    }
    
    public List<Map.Entry<String, Database>> getSortDatabases(){
        List<Map.Entry<String, Database>> infoIds=new ArrayList<>(databases.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Database>>() {   
            @Override
            public int compare(Map.Entry<String, Database> o1, Map.Entry<String, Database> o2) {      
                return (o1.getKey()).compareTo(o2.getKey());
            }
        }); 
        return infoIds;
    }
    
    public boolean getExist(String dbname){
        if(databases==null)
            return false;
        else
            return databases.containsKey(dbname);
        
    }
    
    public Database getDatabase(String dbname){
        if(databases==null){
            return null;
        }else{
            return databases.get(dbname);
        }
    }
    
    public void removeDatabase(String dbname){
        if(databases!=null){
            databases.remove(dbname);
        }
    }
    
    public void clearAll(){
        if(databases!=null){
            this.databases.clear();
        }
    }
    /*
    public void setMarkZero(){
        for (String key : databases.keySet()) {
            Database db = databases.get(key);
            db.setMark(0);
        }
    }*/
    
    //打印所有值
    public void printDatabases(){
        databases.keySet().stream().forEach((key) -> {
            Database db = databases.get(key);
            System.out.println(key+"*"+db.getGUID()+"*"+db.getDay());
        });
    }
    //克隆

    public DatabaseDic cloneDic()  {
        DatabaseDic dbDic=new DatabaseDic();
        if(this.databases!=null){
            databases.keySet().stream().forEach((dbname) -> {
                Database db = databases.get(dbname);
                dbDic.setDatabase(dbname, new Database(db.getGUID(),db.getDay(),db.getSplite()));
            });
        }
        return dbDic;
    }
}
