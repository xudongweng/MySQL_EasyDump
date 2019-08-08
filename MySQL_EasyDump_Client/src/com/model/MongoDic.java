/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xudong.weng
 */
public class MongoDic {
    private static Map<String,MongoObj> mongos=null;
    
    public static void setMongo(IPPort ipport,MongoObj mongo){
        if(mongos==null){
            mongos=new HashMap();
        }
        mongos.put(ipport.getIp()+"_"+ipport.getPort(), mongo);
    }
    
    public static void removeMongo(String ipportkey){
        if(mongos!=null){
            mongos.remove(ipportkey);
        }
    }
    
    public static Map<String,MongoObj> getMongos(){
        return MongoDic.mongos;
    }
    
    public static MongoObj getMongo(String ipportkey){
        if(mongos==null){
            return null;
        }else{
            return mongos.get(ipportkey);
        }
    }
    
    
}
