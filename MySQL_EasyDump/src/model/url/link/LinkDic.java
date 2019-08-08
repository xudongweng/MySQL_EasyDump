/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.url.link;

import java.util.HashMap;
import java.util.Map;


public class LinkDic {
    private static HashMap<String,DatabaseDic> links=null;
    
    public static void setLink(String linkname,DatabaseDic databasedic){
        if(links==null){
            links=new HashMap();
        }
        links.put(linkname, databasedic);
    }
    
    public static DatabaseDic getLink(String linkname){
        if(links==null)
            return null;
        else
            return links.get(linkname);
        
    }
    
    public static boolean getExist(String linkname){
        if(links==null)
            return false;
        else
            return links.containsKey(linkname);
        
    }
    
    public static void removeLink(String linkname){
        if(links!=null){
            links.remove(linkname);
        }
    }
    
    public static HashMap<String,DatabaseDic> getLinks(){
        return LinkDic.links;
    }

    //打印所有值
    public static void printLinks(){
        for (String key : links.keySet()) {
            DatabaseDic databasedic = links.get(key);
            Map<String,Database> databases=databasedic.getDatabases();
            for(String key1 : databases.keySet()){
                Database db=databases.get(key1);
                System.out.println(key1+"*"+db.getGUID()+"*"+db.getDay());
            }
        }
    }
}
