/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.url;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xudong.weng
 */
public class BackupURLDic {
    //备份序列重置
    private Map<Integer,String> backurls=null;
    
    public void setBackupURL(int bakid,String linkname ){
        if(backurls==null){
            this.backurls=new HashMap();
        }
        this.backurls.put(bakid, linkname);
    }
    
    public String getBackupURL(int bakid){
        if(backurls==null){
            return null;
        }else{
            return this.backurls.get(bakid);
        }
    }
    
    public List<Map.Entry<Integer,String>> getSortBackupURLs(){
        List<Map.Entry<Integer,String>> listsort=new ArrayList<>(this.backurls.entrySet());
         Collections.sort(listsort, new Comparator<Map.Entry<Integer,String>>(){
            @Override
            public int compare(Map.Entry<Integer, String> t1, Map.Entry<Integer, String> t2) {
                 return (t1.getKey()).compareTo(t2.getKey());
            }
         });
         return listsort;
    }
    
    public Map<Integer,String> getBackupURLs(){
        return this.backurls;
    }
}
