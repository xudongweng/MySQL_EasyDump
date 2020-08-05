/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.url.link.uuid;

import java.util.HashMap;
import java.util.Map;


public class UUIDDic {
    private static Map<String,TableDic> uuids=null;
    public static void setUUID(String uuid,TableDic tabledic){
        if(uuids==null){
            uuids=new HashMap();
        }
        uuids.put(uuid, tabledic);
    }
    
    public static Map<String,TableDic> getUUIDs(){
            return uuids;
    }
    
    
    
    
    public static TableDic getUUID(String uuid){
        if(uuids==null){
            return null;
        }else{
            return uuids.get(uuid);
        }
    }
    //打印所有值
    public static void printUUIDs(){
        for (String key : uuids.keySet()) {
            TableDic tabledic = uuids.get(key);
            Map<String,Table> tables=tabledic.getTables();
            for(String key1 : tables.keySet()){
                System.out.println(key+"*"+key1+"*"+tables.get(key1));
            }
        }
    }
}
