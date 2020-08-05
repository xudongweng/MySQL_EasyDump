/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.url.link.uuid;

import java.util.HashMap;
import java.util.Map;


public class TableDic {
    private Map<String,Table> tables=null;

    public void setTable(String tablename,Table table){
        if(this.tables==null){
            this.tables=new HashMap();
        }
        this.tables.put(tablename, table);
    }

    public Table getTable(String tablename){
        if(this.tables==null){
            return null;
        }else{
            return this.tables.get(tablename);
        }
    }
    
    
    public Map<String,Table> getTables() {
        return tables;
    }


    
}
