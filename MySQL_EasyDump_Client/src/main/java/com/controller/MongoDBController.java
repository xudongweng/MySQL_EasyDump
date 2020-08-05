/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.helper.NowString;
import com.helper.file.FileHelper;
import com.model.MongoObj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;

/**
 *
 * @author xudong.weng
 */
public class MongoDBController {
    
    //<editor-fold defaultstate="collapsed" desc="将日志去重信息从MongoDB读出">
    public static List getLogInfo(String key,MongoObj mongo){
        List list=MongoDBHelper.getDistinctCol(mongo.getMserver(), mongo.getMport(), mongo.getMdb(), "launghlog",key,mongo.getMuser(), mongo.getMpwd());
        
        Collections.sort(list,new Comparator<String>(){
            public int compare(String arg0, String arg1) {  
                return arg1.compareTo(arg0);  
            } 
        });
        //Collections.reverse(list);
        return list;
    }
    //</editor-fold>
    
    public static String getLog(Document doc,MongoObj mongo){
        return MongoDBHelper.findDocument(mongo.getMserver(), mongo.getMport(), mongo.getMdb(), "launghlog", doc, mongo.getMuser(), mongo.getMpwd());
    }
    
}
